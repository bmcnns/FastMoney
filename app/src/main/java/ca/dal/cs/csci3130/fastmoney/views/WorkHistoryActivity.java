package ca.dal.cs.csci3130.fastmoney.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.models.WorkHistoryRecycler;

public class WorkHistoryActivity extends AppCompatActivity implements View.OnClickListener{

    private final String TAG= "WorkHistoryActivity";

    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> dates = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_history);
        Log.d(TAG, "onCreate: started");

        fillJobsHistory();
    }

    public void fillJobsHistory(){
        titles.add("Walk Dog");
        titles.add("Mow Lawn");
        dates.add("December 3, 2020");
        dates.add("June 14, 2020");
        Log.d(TAG, "Lists filled");
        initWorkHistoryRecycler();
    }

    public void initWorkHistoryRecycler(){
        Log.d(TAG, "Starting workHistoryRecycler");
        RecyclerView recyclerView = findViewById(R.id.jobsList);
        WorkHistoryRecycler workHistoryRecycler= new WorkHistoryRecycler(titles,dates, this);
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
}
