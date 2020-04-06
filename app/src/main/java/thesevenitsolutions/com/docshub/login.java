package thesevenitsolutions.com.docshub;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import thesevenitsolutions.com.docshub.pojo.user;
import thesevenitsolutions.com.docshub.pojo.user_signup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.astritveliu.boom.Boom;



public class login extends AppCompatActivity {
    Button button,btnforgotpassword;
    Context ctx=this;
    TextView txtsignup;
    EditText usernamelog,passwordlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        allocatememory();
        setevent();
    }

    private void setevent() {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(validateinput()) {
                        loginuser();
                    }
                }
            });
            txtsignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent signinintent=new Intent(ctx,signup.class);
                    startActivity(signinintent);

                }
            });
            btnforgotpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ctx,forgetpassword.class));

                }
            });
        }

    private void loginuser() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("ONE MOMENT PLEASE");
        progressDialog.show();

        String userName=usernamelog.getText().toString().trim();
        String password=passwordlog.getText().toString().trim();

        apiInterface service = apIclient.getClient().create(apiInterface.class);

        user user2= new user(userName,password);

        Call<user_signup>call = service.loginUser(user2.getUserName(),user2.getPassword());
        call.enqueue(new Callback<user_signup>() {
            @Override
            public void onResponse(Call<user_signup> call, Response<user_signup> response) {
                progressDialog.dismiss();
                if (response.body()== null) {
                    Log.d("signin",response.errorBody().toString());

                }
                else{
                    if (response.body().isStatus()) {
                        prefrence.getInstance(ctx).userLogin(response.body().getData());
                        Log.d("signin", response.body().getData().getToken());
                        startActivity(new Intent(ctx, homescreen.class));
                        finish();
                        Toast.makeText(ctx, "success:" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(ctx, "error" + response.body().getError(), Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<user_signup> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ctx,t.getMessage(),Toast.LENGTH_LONG).show();
            }

        });
    }


    private void allocatememory() {
        button=findViewById(R.id.login);
        new Boom(button);
        txtsignup=findViewById(R.id.txtsignup);
        usernamelog=findViewById(R.id.txtusernamelog);
        passwordlog=findViewById(R.id.passwordlog);
        btnforgotpassword=findViewById(R.id.btnforgotpass);
    }

    private boolean validateinput() {
        String password = passwordlog.toString().trim();
        String username = usernamelog.toString().trim();
        boolean isvalid = true;
        if (username.length() == 0){
            usernamelog.setError("Please Enter Username!");
            isvalid = false;
        }
        else if(password.length()<8) {
            passwordlog.setError("Password Should Be Minimum 8 char Long!");
            isvalid = false;
        }

        return isvalid;
    }



}
