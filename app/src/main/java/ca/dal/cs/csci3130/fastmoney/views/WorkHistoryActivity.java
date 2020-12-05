package ca.dal.cs.csci3130.fastmoney.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.models.Job;
import ca.dal.cs.csci3130.fastmoney.models.WorkHistoryRecycler;

public class WorkHistoryActivity extends AppCompatActivity implements ValueEventListener {

    private ArrayList<Job> jobs = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_history);

        //method to fill the list of jobs
        fillJobsHistory();
    }

    //TODO add jobs from database
    public void fillJobsHistory(){

        //temp until jobs done
        Job test= new Job("Walk Dog", 5, null, null,null,null, null);
        Job test2= new Job("eat tacos", 6, null, null,null,null, null);
        jobs.add(test);
        jobs.add(test2);

        initWorkHistoryRecycler();
    }

    //creates the recycler list
    public void initWorkHistoryRecycler(){
        RecyclerView recyclerView = findViewById(R.id.jobsList);
        WorkHistoryRecycler workHistoryRecycler= new WorkHistoryRecycler(jobs, this);
        recyclerView.setAdapter(workHistoryRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //redirects user to the main activity
    public void redirectLandingPage(View view){
        Intent Redirect = new Intent(this, LandingPageActivity.class);
        startActivity(Redirect);
    }

    //redirects user to the main activity
    public void redirectJobPage(Job job){
        Intent Redirect = new Intent(this, LandingPageActivity.class);
        startActivity(Redirect);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}

