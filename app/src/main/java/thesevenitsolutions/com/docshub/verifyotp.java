package thesevenitsolutions.com.docshub;

import androidx.appcompat.app.AppCompatActivity;
import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class verifyotp extends AppCompatActivity {
    Button submit;
    Context ctx=this;
    OtpTextView otp_verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyotp);
        allocatememory();
        setevent();
    }

    private void setevent() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateinput()){
                    verfication();
                }
            }
        });
    }

    private void verfication() {
    }

    private boolean validateinput() {
        boolean isvalid=true;
        if(otp_verify.getOTP()==null){

        }

        return isvalid;

    }

    private void allocatememory() {
        submit=findViewById(R.id.submit);
        otp_verify=findViewById(R.id.otp_verify);
    }
}
