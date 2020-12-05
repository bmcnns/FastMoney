package ca.dal.cs.csci3130.fastmoney.models;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.views.JobActivity;

public class WorkHistoryRecycler extends RecyclerView.Adapter<WorkHistoryRecycler.ViewHolder> {

    private String TAG= "RecyclerView";
    ArrayList<Job> jobs= new ArrayList<>();
    Context context;

    public WorkHistoryRecycler(ArrayList<Job> jobs, Context context){
        this.jobs=jobs;
        this.context=context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout parent;
        TextView jobTitle;
        TextView Employer;

        public ViewHolder(View view){
            super(view);
            parent=view.findViewById(R.id.parent);
            jobTitle=view.findViewById(R.id.jTitle);
            Employer=view.findViewById(R.id.Employer);
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position){
        holder.jobTitle.setText(jobs.get(position).getTitle());

        String employerName = jobs.get(position).getEmployer().getFirstName()+" "+jobs.get(position).getEmployer().getLastName();
        holder.Employer.setText(employerName);

        Log.d(TAG, "making job");
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirect= new Intent(context, JobActivity.class);
                redirect.putExtra("Job", (Serializable) jobs.get(position));
                context.startActivity(redirect);
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }
}
