package ca.dal.cs.csci3130.fastmoney.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import ca.dal.cs.csci3130.fastmoney.R;

public class JobActivity extends AppCompatActivity {
    String jobId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        this.jobId = getIntent().getExtras().getString("jobId");
    }
}
