package ca.dal.cs.csci3130.fastmoney.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.models.Job;
import ca.dal.cs.csci3130.fastmoney.models.WorkHistoryRecycler;

public class WorkHistoryActivity extends AppCompatActivity implements View.OnClickListener{

    private final String TAG= "WorkHistoryActivity";

    private ArrayList<Job> jobs = new ArrayList<>();



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_history);
        Log.d(TAG, "onCreate: started");

        fillJobsHistory();
    }

    public void fillJobsHistory(){
        Date today = new Date();
        today.getTime();
        String[] images= {" "};
        Job test= new Job("Walk Dog", 5, null, null,null,null, null);
        test.setEndDate(today);
        Job test1= new Job("Walk Dog", 6, null, null,null,null, null);
        test1.setEndDate(today);
        jobs.add(test);

        Log.d(TAG, "Lists filled");
        initWorkHistoryRecycler();
    }

    public void initWorkHistoryRecycler(){
        Log.d(TAG, "Starting workHistoryRecycler");
        RecyclerView recyclerView = findViewById(R.id.jobsList);
        WorkHistoryRecycler workHistoryRecycler= new WorkHistoryRecycler(jobs, this);
        recyclerView.setAdapter(workHistoryRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d(TAG, "Starting workHistoryRecycler");
    }

    @Override
    public void onClick(View view) {

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
}
