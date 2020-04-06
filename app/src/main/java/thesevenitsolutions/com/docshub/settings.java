package thesevenitsolutions.com.docshub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.Validators;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.astritveliu.boom.Boom;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import javax.xml.validation.Validator;

public class settings extends AppCompatActivity {
    TextView editprofile,changepassword,about,help,logout;
    Context ctx=this;
    EditText oldpassword,newpassword,confirmpassword;
    MaterialAlertDialogBuilder b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        b1 = new MaterialAlertDialogBuilder(ctx);
        allocatememory();
        setevent();

    }

//    private boolean validate() {
//        String Oldpassword=oldpassword.getText().toString().trim();
//        String Newpassword=newpassword.getText().toString().trim();
//        String Confirmpassword=confirmpassword.getText().toString().trim();
//        boolean isValid = true;
//        if(Oldpassword.length()<8)
//        {
//            oldpassword.setError("Password is required and must be at least 8 character long");
//            isValid = false;
//        }
//        if(Newpassword.length()<8)
//        {
//            newpassword.setError("Password is required and must be at least 8 character long");
//            isValid = false;
//        }
//        if(Confirmpassword.length()<8)
//        {
//            confirmpassword.setError("Password is required and must be at least 8 character long");
//            isValid = false;
//        }
//
//        return isValid;
//    }
//
    private void setevent() {
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = LayoutInflater.from(ctx);
                final View CustomDialog = inflater.inflate(R.layout.changepassworddialog,null);
                b1.setView(CustomDialog);
                b1.setPositiveButton("Change password", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                            EditText oldpassword, newpassword1;
                            oldpassword = CustomDialog.findViewById(R.id.oldpassword);
                            newpassword1 = CustomDialog.findViewById(R.id.newpassword1);
                            String Username = oldpassword.getText().toString();
                            String Password = newpassword1.getText().toString();
                            Toast.makeText(ctx, "Username =" + Username + " Password = "
                                    + Password, Toast.LENGTH_SHORT).show();
                        }

                });
                b1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ctx,"Cancel button clicked",Toast.LENGTH_SHORT).show();
                    }
                });
                b1.create().show();
            }
        });
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editprofileintent=new Intent(ctx,editprofile.class);
                startActivity(editprofileintent);
            }
        });
    }

    private void allocatememory() {
        editprofile=findViewById(R.id.editprofile);
        changepassword=findViewById(R.id.changepassword);
        about=findViewById(R.id.about);
        help=findViewById(R.id.help);
        logout=findViewById(R.id.logout);
        oldpassword=findViewById(R.id.oldpassword);
        newpassword=findViewById(R.id.newpassword1);
        confirmpassword=findViewById(R.id.confirmpassword1);

    }
}
