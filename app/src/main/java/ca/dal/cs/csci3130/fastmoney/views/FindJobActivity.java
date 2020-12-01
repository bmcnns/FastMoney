package ca.dal.cs.csci3130.fastmoney.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import ca.dal.cs.csci3130.fastmoney.R;

public class FindJobActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_job);
    }

    public void redirectToWorkPage() {
        Intent redirect = new Intent(this, WorkActivity.class);
        startActivity(redirect);
    }

    public void redirectToLogInPage() {
        Intent redirect = new Intent(this, LogInActivity.class);
        startActivity(redirect);
    }

    public void redirectToFindJobPage() {
        Intent redirect = new Intent(this, FindJobActivity.class);
        startActivity(redirect);
    }

    public void redirectToProfilePage() {
        Intent redirect = new Intent(this, WorkActivity.class);
        startActivity(redirect);
    }

    public void redirectToJobPage() {
        Intent redirect = new Intent(this, WorkActivity.class);
        startActivity(redirect);

    }

    public void redirectToPostJobPage() {
        Intent redirect = new Intent(this, WorkActivity.class);
        startActivity(redirect);
    }
}
