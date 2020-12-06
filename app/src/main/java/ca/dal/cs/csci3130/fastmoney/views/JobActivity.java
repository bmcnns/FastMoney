package ca.dal.cs.csci3130.fastmoney.views;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.models.Job;
import ca.dal.cs.csci3130.fastmoney.models.User;
import ca.dal.cs.csci3130.fastmoney.views.FindJobActivity;
import ca.dal.cs.csci3130.fastmoney.views.JobActivity;

public class JobActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    private String jobId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_job);
        firebaseFirestore = FirebaseFirestore.getInstance();

        jobId = getIntent().getExtras().get("jobId").toString();
        displayInformation();
    }

    private Job getJobFromData(Map<String, Object> data) {
        Job result = null;

        result = new Job(
                (String)data.get("title"),
                (int)Math.floor((long)data.get("payRate")),
                (String)data.get("description"),
                (List<String>)data.get("images"),
                new User("John", "Callinger", "john.callinger@example.com", "Halifax, NS", "4724090012345678", "profile.png", 5.0f, 5.0f),
                null
        );

        return result;
    }


    private void displayInformation() {
        firebaseFirestore.collection("jobs")
                .document("test")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (!task.isSuccessful()) {
                        } else {
                            Job job = getJobFromData(task.getResult().getData());
                            ((TextView) findViewById(R.id.jobDetail_title)).setText(job.getTitle());
                            ((TextView) findViewById(R.id.jobDetail_details)).setText("$" + job.getPayRate() + "/hr | " + "0 kms away.");
                            ((TextView) findViewById(R.id.jobDetail_description)).setText(job.getDescription());
                            ((TextView) findViewById(R.id.jobDetail_postedDate)).setText(job.getPostedDate().toString());
                        }
                    }
                });
    }
}