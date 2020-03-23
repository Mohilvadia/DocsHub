package thesevenitsolutions.com.docshub;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class otp extends AppCompatActivity {

    Context ctx=this;
@BindView(R.id.btnsubmit) Button btnsubmit;
@BindView(R.id.resendotp) TextView resendotp;
@Optional
@OnClick({R.id.btnsubmit,R.id.resendotp}) public void OnClick(View v){
        int id = v.getId();
        if(R.id.btnsubmit==id){
        Intent loginintent = new Intent(ctx,homescreen.class);
        startActivity(loginintent);
        finish();
        Toast.makeText(ctx,"Successfully Logged In",Toast.LENGTH_SHORT).show();
    }
        else{
        Toast.makeText(ctx,"OTP Sent To Your Phone No.",Toast.LENGTH_SHORT).show();

    }
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
    }
}
