package ca.dal.cs.csci3130.fastmoney.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import ca.dal.cs.csci3130.fastmoney.R;

public class LandingPageActivity extends AppCompatActivity {

    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(), LogInActivity.class));
            finish();
        }
    }

    public void signOut(View view) {
        fAuth.signOut();
        Intent redirect = new Intent(this, LogInActivity.class);
        startActivity(redirect);
    }

    public void redirectToWorkPage(View view) {
        Intent redirect = new Intent(this, WorkActivity.class);
        startActivity(redirect);
    }

    public void redirectToLogInPage(View view) {
        Intent redirect = new Intent(this, LogInActivity.class);
        startActivity(redirect);
    }

    public void redirectToFindJobPage(View view) {
        Intent redirect = new Intent(this, FindJobActivity.class);
        startActivity(redirect);
    }

    public void redirectToProfilePage(View view) {
        Intent redirect = new Intent(this, ProfileActivity.class);
        startActivity(redirect);
    }

    public void redirectToHirePage(View view) {
        Intent redirect = new Intent(this, HireActivity.class);
        startActivity(redirect);
    }

    public void redirectToPostJobPage(View view) {
        Intent redirect = new Intent(this, AddJobActivity.class);
        startActivity(redirect);
    }

}
