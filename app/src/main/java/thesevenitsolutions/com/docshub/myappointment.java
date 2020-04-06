package thesevenitsolutions.com.docshub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;

public class myappointment extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context ctx = this;
    private ArrayList<Myappointmentbean> Myappointment =new ArrayList<>();
    String date,time,patientname,drname,remarks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myappointment);
        RecyclerView myappointmentlist = (RecyclerView) findViewById(R.id.my_recycler_view);
        myappointmentlist.setLayoutManager(new LinearLayoutManager(this));
        String[] names={"gohil","jinu","kkgohil"};
        myappointmentlist.setAdapter(new Myappointmentadapter(names));

    }
}
