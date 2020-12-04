package ca.dal.cs.csci3130.fastmoney.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.dal.cs.csci3130.fastmoney.R;

public class WorkHistoryRecycler extends RecyclerView.Adapter<WorkHistoryRecycler.ViewHolder>{

    private String TAG= "RecyclerView";
    ArrayList<String> titles= new ArrayList<>();
    ArrayList<String> dates= new ArrayList<>();
    //Context context;

    public WorkHistoryRecycler(ArrayList<String> titles,ArrayList<String> dates, Context context){
        this.titles=titles;
        this.dates=dates;
        //this.context=context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout constraintLayout;
        FrameLayout frameLayout;
        LinearLayout linearLayout;
        TextView jobTitle;
        TextView jobDate;

        public ViewHolder(View view){
            super(view);
            constraintLayout=view.findViewById(R.id.constraintLayout);
            jobTitle=view.findViewById(R.id.jobTitle);
            jobDate=view.findViewById(R.id.jobDate);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_element_temp, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.jobTitle.setText(titles.get(position));

        holder.jobDate.setText(dates.get(position));

        /*holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirect = new Intent(this, JobActivity.class);
                this.startActivity(redirect);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
}
