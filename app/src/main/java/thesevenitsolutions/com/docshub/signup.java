package thesevenitsolutions.com.docshub;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thesevenitsolutions.com.docshub.pojo.user;
import thesevenitsolutions.com.docshub.pojo.user_signup;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 100;
    EditText txtname,txtemail,txtmobile,txtpassword,txtusername;
    Button btnsignup;
    TextView txtsignin;
    Context ctx=this;
    TextView userchange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        allocatememory();
        setevent();
        checkpermission();
    }

    private void checkpermission() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED) {
                String[] PermissionList = {Manifest.permission.INTERNET,
                        Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(PermissionList,PERMISSION_REQUEST_CODE);
            }

        }
        }



    private void setevent() {
        txtmobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (txtmobile.length() == 3 || txtmobile.length() == 7) {
                    txtmobile.append("-");
                }
            }
        });
        txtsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logintent=new Intent(ctx,login.class);
                startActivity(logintent);
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ValidateInput())
                    signup_user();
            }
        });

    }
    private boolean ValidateInput() {
        String Email = txtemail.getText().toString().trim();
        String Password = txtpassword.getText().toString().trim();
        String valemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String Mobileno=txtmobile.getText().toString().trim();
        boolean isValid = true;
        if(txtname.length()==0) {
            txtname.setError("Please Fill Up Name");
            isValid = false;
        }
        else if (txtusername.length()==0) {
            txtusername.setError("Please Fill Up Username");
            isValid = false;
        }
        else if(Mobileno.length()==0) {
            txtmobile.setError("Please fill Up Phone Number");
            isValid = false;
        }
        else if(Mobileno.length()!=10) {
            txtmobile.setError("Please Add Only 10 Digit");
            isValid = false;
        }
        if(Email.length()==0)
        {
            txtemail.setError("email is required");
            isValid = false;
        }
        else if (!Email.matches(valemail))
        {
            txtemail.setError("Please enter Correct Email Address");
            isValid = false;
        }
        else if(Password.length()<8)
        {
            txtpassword.setError("Password is required and must be at least 8 character long");
            isValid = false;
        }
        return isValid;
    }
    private void signup_user() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        String name = txtname.getText().toString().trim();
        String email = txtemail.getText().toString().trim();
        String mobile = txtmobile.getText().toString().trim();
        String password = txtpassword.getText().toString().trim();
        String username =txtusername.getText().toString().trim();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(common.getbaseurl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface service = retrofit.create(apiInterface.class);
        user user = new user(name,username,email,mobile,password);
        Call<user_signup> call = service.createUser(
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getMobile(),
                user.getPassword());
        call.enqueue(new Callback<user_signup>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<user_signup> call, Response<user_signup> response) {
                progressDialog.dismiss();
                assert response.body() != null;
                if(response.body().isStatus()){
                    prefrence.getInstance(ctx).userLogin(response.body().getData());
                    Intent homeintent=new Intent(ctx,homescreen.class);
                    startActivity(homeintent);
                    finish();
                    Toast.makeText(signup.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("signup",response.body().getData().toString());
                    Log.d("signup",response.body().getData().getToken());
                 }
                else{
                    Toast.makeText(ctx,response.body().getMessage(),Toast.LENGTH_LONG).show();
                    Log.d("signup",response.body().getError().toString());
                }

            }

            @Override
            public void onFailure(Call<user_signup> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ctx,"no response from server", Toast.LENGTH_LONG).show();

            }
        });
    }

            private void allocatememory() {
        txtname=findViewById(R.id.txtname);
        txtemail=findViewById(R.id.txtemail);
        txtmobile=findViewById(R.id.txtmobileno);
        txtpassword=findViewById(R.id.txtpassword);
        btnsignup=findViewById(R.id.btnsignup);
        txtusername=findViewById(R.id.txtusername);
        txtsignin=findViewById(R.id.txtsignin);
        userchange=findViewById(R.id.txtuserchange);
    }
}
