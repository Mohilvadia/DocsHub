package thesevenitsolutions.com.docshub;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thesevenitsolutions.com.docshub.pojo.user_signup;

public class homescreen extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    Context ctx=this;
    ImageView imghos,imgdoc,imgdepartment,imgappoint;
    DrawerLayout d1;
    TextView userchange;
    ActionBarDrawerToggle abdt;
    Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        allocatememory();
        setevent();
        navview();

        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.fade_in);
        imgdepartment.startAnimation(myanim);
        imgappoint.startAnimation(myanim);
        imghos.startAnimation(myanim);
        imgdoc.startAnimation(myanim);
    }

    private void navview() {

        d1= findViewById(R.id.drawer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        abdt=new ActionBarDrawerToggle(this,d1,toolbar, R.string.open,R.string.close);

        abdt.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        d1.addDrawerListener(abdt);
        abdt.syncState();
        NavigationView navview=(NavigationView) findViewById(R.id.nav_view);
        navview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int items = item.getItemId();
                if(items==R.id.myappoint){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri data = Uri.parse("mailto:"
                            + "thesevenitsolutions@gmail.com"
                            + "?subject=" + "Feedback" + "&body=" + "");
                    intent.setData(data);
                    startActivity(intent);

                    d1.closeDrawer(GravityCompat.START);
                }
                if(items==R.id.mydoc){        d1.closeDrawer(GravityCompat.START);
                }
                if(items==R.id.healthblog){        d1.closeDrawer(GravityCompat.START);
                }
                if(items==R.id.setting){
                    d1.closeDrawer(GravityCompat.START);
                }
                if(items==R.id.logout){
                    logout();
                    d1.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });
        navview.bringToFront();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item)|| super.onOptionsItemSelected(item);
    }
    private void setevent() {
        imgdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent finddocintent=new Intent(ctx,finddoctor.class);
                startActivity(finddocintent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
        imghos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findhosintent=new Intent(ctx,findhospital.class);
                startActivity(findhosintent);
            }
        });
        imgappoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appointintent= new Intent(ctx,finddoctor.class);
                startActivity(appointintent);
            }
        });
        imgdepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent departmentintent=new Intent(ctx,department.class);
                startActivity(departmentintent);
            }
        });



    }

    private void logout() {
        if (prefrence.getInstance(ctx).isLoggedIn()){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
            alertDialogBuilder.setMessage("Are you sure,You want to Logout?");
            alertDialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                            apiInterface service = apIclient.getClient().create(apiInterface.class);

                            Call<user_signup> call = service.logout("Bearer "+prefrence.getInstance(ctx).getTOken());

                            call.enqueue(new Callback<user_signup>() {
                                @Override
                                public void onResponse(Call<user_signup> call, Response<user_signup> response) {
                                    if (response.body().getError() != null) {
                                        Log.d("Logout", response.body().getError().toString());
                                    }
                                    else {
                                        startActivity(new Intent(ctx, login.class));
                                        finish();
                                        Toast.makeText(ctx, "LOGOUT Successfully!", Toast.LENGTH_LONG).show();
                                        prefrence.getInstance(ctx).logout();
                                    }

                                }

                                @Override
                                public void onFailure(Call<user_signup> call, Throwable t) {
                                    Toast.makeText(ctx,t.getMessage(),Toast.LENGTH_SHORT).show();

                                }
                            });

                                                   }
                    });

            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
        else{
            Toast.makeText(ctx, "Please LogIn First", Toast.LENGTH_LONG).show();

        }

               }


    private void allocatememory() {
        imghos= findViewById(R.id.imgfindhos);
        imgdoc= findViewById(R.id.imgfinddoc);
        imgdepartment= findViewById(R.id.imgdepartment);
        imgappoint= findViewById(R.id.imgappoint);
        userchange=findViewById(R.id.txtuserchange);
        toolbar=findViewById(R.id.toolbar);

    }


}
