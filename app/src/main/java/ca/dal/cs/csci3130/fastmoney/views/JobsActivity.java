package ca.dal.cs.csci3130.fastmoney.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.models.Job;
import ca.dal.cs.csci3130.fastmoney.models.JobType;

public class JobsActivity extends AppCompatActivity {
    /**
     * Initializes the app. Main entry point.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        /*Job job = new Job(
                "Sample Job #1",
                "Sample Employer #1",
                "Halifax, Nova Scotia",
                20.00,
                JobType.FULLTIME
        );

        Job job2 = new Job(
                "Sample Job #2",
                "Sample Employer #2",
                "Halifax, Nova Scotia",
                20.00,
                JobType.PARTTIME
        );
        */

        //addJobToDatabase(job);
        //addJobToDatabase(job2);
        displayJobsFromDatabase();
    }

    public void addJobToDatabase(Job job) {
        /*
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("title", job.getTitle());
        payload.put("employer", job.getEmployer());
        payload.put("location", job.getLocation());
        payload.put("payRate", job.getPayRate());
        payload.put("jobType", job.getJobType().toString());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String randomJobId = UUID.randomUUID().toString();
        db.collection("jobs").document(randomJobId).set(payload);
         */
    }

    public List<Job> displayJobsFromDatabase() {
        /*
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("jobs").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<Job> result = new ArrayList<>();

                    for (QueryDocumentSnapshot job : task.getResult()) {
                        Map<String, Object> jobData = job.getData();
                        JobType jobType = jobData.get("jobType").toString().equals("PARTTIME") ? JobType.PARTTIME : JobType.FULLTIME;

                        Job retrieved = new Job(
                                (String) jobData.get("title"),
                                (String) jobData.get("employer"),
                                (String) jobData.get("location"),
                                (Double) jobData.get("payRate"),
                                jobType
                        );

                        displayJob(retrieved);
                    }
                }
            }
        });
*/
        return null;
    }

    public void displayJob(Job job) {
        /*
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.activity_job, null);

        // fill in any details dynamically here
        TextView titleText = (TextView) v.findViewById(R.id.title);
        titleText.setText(job.getTitle());

        TextView employerText = (TextView) v.findViewById(R.id.employer);
        employerText.setText(job.getEmployer());

        TextView jobTypeText = (TextView) v.findViewById(R.id.jobType);
        jobTypeText.setText(job.getJobType().toString());

        TextView locationText = (TextView) v.findViewById(R.id.location);
        locationText.setText(job.getLocation());

        TextView payRateText = (TextView) v.findViewById(R.id.payRate);
        payRateText.setText(Double.toString(job.getPayRate()) + "/hr");

        ViewGroup parent = (ViewGroup) findViewById(R.id.jobsRoot);
        parent.addView(v);
         */
    }
}