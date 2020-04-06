package thesevenitsolutions.com.docshub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Myappointmentadapter extends RecyclerView.Adapter<Myappointmentadapter.MyappointmentViewHolder> {
    private String[] data;
    public Myappointmentadapter(String[] names){
        this.data=data;
    }
    @NonNull
    @Override
    public MyappointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.myappointmentrow,parent,false);
        return new MyappointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyappointmentViewHolder holder, int position) {
        String appointmentdate =data[position];
        holder.appointmentdate.setText(appointmentdate);
        String appointmenttime =data[position];
        holder.appointmenttime.setText(appointmenttime);
        String patientname =data[position];
        holder.patientname.setText(patientname);
        String appointmentdocname =data[position];
        holder.appointmentdocname.setText(appointmentdocname);
        String remarks =data[position];
        holder.remarks.setText(remarks);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyappointmentViewHolder extends RecyclerView.ViewHolder {
        TextView appointmentdate,appointmenttime,patientname,appointmentdocname,remarks;
        public MyappointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            appointmentdate=(TextView) itemView.findViewById(R.id.appointmentdate);
            appointmenttime=(TextView) itemView.findViewById(R.id.appointmenttime);
            patientname=(TextView) itemView.findViewById(R.id.patientname);
            appointmentdocname=(TextView) itemView.findViewById(R.id.appointmentdocname);
            remarks=(TextView) itemView.findViewById(R.id.remarks);
        }
    }



}
