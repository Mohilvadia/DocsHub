package thesevenitsolutions.com.docshub;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private static final int GALLARY_REQUEST = 10;
    private static final int PERMISSION_REQUEST_CODE = 50;
    Context ctx = this;
    String mobilevalidate ="^[0-9]$";
    int ida=10;
    @BindView(R.id.login) Button btnlogin;
@BindView(R.id.mobileno) EditText mobileno;

    @Optional
    @OnClick({R.id.login})
    public void OnClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.login :
                if (mobileno.length()>ida || mobileno.length()<ida) {
                    mobileno.setError("please enter 10 digits");
                }
                else {
                    Intent loginintent = new Intent(ctx, otp.class);
                    finish();
                    startActivity(loginintent);
                    }
                break;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }



}

