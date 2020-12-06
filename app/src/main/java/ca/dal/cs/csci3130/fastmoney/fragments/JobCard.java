package ca.dal.cs.csci3130.fastmoney.fragments;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JobCard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JobCard extends Fragment implements View.OnClickListener {
    FirebaseFirestore firebaseFirestore;
    private String jobId;

    public JobCard() {
        // Required empty public constructor
    }

    public static JobCard newInstance(String jobId) {

        JobCard fragment = new JobCard();
        Bundle args = new Bundle();
        args.putString("jobId", jobId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();
        if (getArguments() != null) {
            jobId = getArguments().getString("jobId");
        }

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
                            ((TextView) getView().findViewById(R.id.jobCard_title)).setText(job.getTitle());
                            ((TextView) getView().findViewById(R.id.jobCard_payRate)).setText("$" + job.getPayRate() + "$/hr");
                            ((TextView) getView().findViewById(R.id.jobCard_distance)).setText("left undefined");
                            ((TextView) getView().findViewById(R.id.jobCard_time)).setText("left undefined");
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_job_card, container, false);

        MaterialButton button = v.findViewById(R.id.jobCard_viewJobButton);
        button.setOnClickListener(this);

        // Inflate the layout for this fragment
        return v;
    }

    public void viewJobOverview(View view) {
        Intent redirect = new Intent(getView().getContext(), JobActivity.class);
        redirect.putExtra("jobId", jobId);
        startActivity(redirect);
    }

    public void onClick(View view) {
        viewJobOverview(view);
    }
}