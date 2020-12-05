package ca.dal.cs.csci3130.fastmoney.views;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.models.Job;

public class JobActivity extends AppCompatActivity implements Serializable {
    Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("Job")){
            job= (Job) getIntent().getSerializableExtra("Job");
            setJob();
        }
    }

    private void setJob(){
        TextView title=findViewById(R.id.title);
        title.setText(job.getTitle());
    }
}
