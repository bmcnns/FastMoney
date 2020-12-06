package ca.dal.cs.csci3130.fastmoney.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.models.Job;
import ca.dal.cs.csci3130.fastmoney.models.WorkHistoryRecycler;

public class WorkHistoryActivity extends AppCompatActivity {

    private ArrayList<Job> jobs = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_history);


        fillJobsHistory();
    }

    //fills array with jobs
    public void fillJobsHistory(){

        //temporary jobs
        Date today = new Date();
        today.getTime();
        Job test= new Job("Walk Dog", 5, null, null,null,null, today);
        Job test1= new Job("Mow Lawn", 6, null, null,null,null, today);
        jobs.add(test);
        jobs.add(test1);

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
}

