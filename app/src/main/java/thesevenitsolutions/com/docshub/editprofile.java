package thesevenitsolutions.com.docshub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editprofile extends AppCompatActivity {
    EditText username,firstname,address,lastname,phonenumber,email;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        allocatememory();
        setevent();
    }

    private void setevent() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { {
                if (validate()==true){
                    profileupdate();
                }
                }
            }
        });
    }

    private void profileupdate() {

    }

    private boolean validate() {
        String Email = email.getText().toString().trim();
        String valemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String Mobileno=phonenumber.getText().toString().trim();
        boolean isValid = true;
        if(username.getText().toString().length() == 0) {
            username.setError("firstname cannot be empty");
            isValid = false;
        }
        if (firstname.getText().toString().length() == 0) {
            firstname.setError("username cannot be empty");
            isValid = false;
        }
        if(phonenumber.getText().toString().length() == 0) {
            phonenumber.setError("phone number cannot be empty");
            isValid = false;
        }
        if(email.getText().toString().length() == 0)
        {
            email.setError("email is required");
            isValid = false;
        }
        if (!Email.matches(valemail))
        {
            email.setError("Please enter Correct Email Address");
            isValid = false;
        }
        if(lastname.length()==0)
        {
            lastname.setError("last name cannot be empty ");
            isValid = false;
        } if(address.length()==0)
        {
            address.setError("address cannot be empty ");
            isValid = false;
        }
        return isValid;
    }

    private void allocatememory() {
        username=findViewById(R.id.username_editprofile);
        firstname=findViewById(R.id.firstname_editprofile);
        address=findViewById(R.id.address_editprofile);
        lastname=findViewById(R.id.lastname_editprofile);
        phonenumber=findViewById(R.id.phonenumber_editprofile);
        email=findViewById(R.id.email_editprofile);
        save=findViewById(R.id.save_editprofile);
    }

}
