package thesevenitsolutions.com.docshub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import androidx.appcompat.app.AppCompatActivity;
import thesevenitsolutions.com.docshub.pojo.hospital;
import thesevenitsolutions.com.docshub.pojo.hospitallist;

public class findhospital extends AppCompatActivity{
    Spinner spn;
    Context ctx=this;
    TextView txthospitalname,txthopitaltype;
    RecyclerView redhos;
    private ArrayList<hospital> hospitalArrayList=new ArrayList<>();
    ImageView imghospital,back;
    String name,department;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findhospital);
        allocatememory();
        setevent();

    }


    private void setevent(){
        SearchView.OnQueryTextListener querytext =new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newtext) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText.trim();
                return true;
            }
        };
      //  SearchView.setOnQueryTextListener(querytext);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backintent=new Intent(ctx,homescreen.class);
                startActivity(backintent);
            }
        });
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String spnn=spn.getSelectedItem().toString();
                apiInterface service=apIclient.getClient().create(apiInterface.class);
                hospital hospital=new hospital(spnn);
                Call<hospitallist> call=service.gethospital("Bearer "+prefrence.getInstance(ctx).getTOken(),hospital.getCity());

                call.enqueue(new Callback<hospitallist>() {
                    @Override
                    public void onResponse(Call<hospitallist> call, Response<hospitallist> response) {
                        if(response.body().isStatus()){

                            if(response.body().getData()==null){
                                Toast.makeText(ctx,"No Record Found",Toast.LENGTH_LONG).show();
                            }
                            else{
                                try {
                                    JSONObject obj=new JSONObject(response.toString());
                                    JSONArray jsonobjsect=obj.getJSONArray(response.body().getData().toString());
                                    int totall=jsonobjsect.length();
                                    for(int i=0;i<=totall;i++){
                                        JSONObject object=jsonobjsect.getJSONObject(i);
                                        name=object.getString("name");
                                        department=object.getString("department");
                                        hospital h =new hospital(name,department);
                                        hospitalArrayList.add(h);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                hospitalAdapter adapter=new hospitalAdapter(ctx,hospitalArrayList);
                                redhos.setItemAnimator(new DefaultItemAnimator());
                                redhos.setAdapter(adapter);
                                redhos.setLayoutManager(new GridLayoutManager(ctx,1));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<hospitallist> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    private void allocatememory() {
        txthopitaltype=findViewById(R.id.txthospitaltype);
        txthospitalname=findViewById(R.id.txthospitalname);
        imghospital=findViewById(R.id.imghospital);
        spn=findViewById(R.id.spn);
        back=findViewById(R.id.back);
        redhos=findViewById(R.id.rechos);
    }
}
