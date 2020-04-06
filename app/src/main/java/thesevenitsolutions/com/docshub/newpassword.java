package thesevenitsolutions.com.docshub;

import androidx.appcompat.app.AppCompatActivity;
import in.aabhasjindal.otptextview.OtpTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import thesevenitsolutions.com.docshub.pojo.forget;
import thesevenitsolutions.com.docshub.pojo.forgotpassword;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class newpassword extends AppCompatActivity {
    EditText newpassword,email;
    Button submitpass;
    OtpTextView otp;
    Context ctx=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpassword);
       allocatememory();
       setevent();
    }

    private void allocatememory() {

        newpassword=findViewById(R.id.new_password);
        email=findViewById(R.id.email);
        submitpass=findViewById(R.id.submitpass);
        otp=findViewById(R.id.otp_verify);
    }

    private void setevent() {
        submitpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    changepass();
                }
            }
        });

    }

    private void changepass() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("ONE MOMENT PLEASE");
        progressDialog.show();

        String newpass=newpassword.getText().toString().trim();
        String email1=email.getText().toString().trim();
        String otp1=otp.getOTP();

        apiInterface service = apIclient.getClient().create(apiInterface.class);

        forgotpassword forgetpassword=new forgotpassword(email1,otp1,newpass);

        Call<forget> call = service.resetpassword(prefrence.getInstance(ctx).getTOken(),forgetpassword.getEmail(),forgetpassword.getOtp(),forgetpassword.getNew_password());

        call.enqueue(new Callback<forget>() {
            @Override
            public void onResponse(Call<forget> call, Response<forget> response) {
                if(response.body()==null){
                    Toast.makeText(ctx,"Something Went Wrong!",Toast.LENGTH_LONG).show();
                }
                else{
                    if(response.body().getMessage().equals("One Time Password Is Invalid")){
                        Toast.makeText(ctx,response.body().getMessage(),Toast.LENGTH_LONG).show();
                    }
                    else if(response.body().getMessage().equals("Email Is Invalid")){
                        Toast.makeText(ctx,response.body().getMessage(),Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(ctx,response.body().getMessage(),Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ctx,homescreen.class));
                    }
                }
            }

            @Override
            public void onFailure(Call<forget> call, Throwable t) {

            }
        });
    }

    private boolean validate(){
        String valemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        boolean temp=true;
         if(newpassword.getText().toString().trim().length()<8){
            newpassword.setError("Password must be 8 Character Long");
                temp=false;
        }
         else if(email.getText().toString().trim().matches(valemail)) {
             email.setError("Please Enter valid Email Address");
            temp=false;
         }
         else if(otp.getOTP().length()<6)
         {
             submitpass.setEnabled(false);
                temp =false;
         }
        return temp;
    }
}
