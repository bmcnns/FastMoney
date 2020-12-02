package ca.dal.cs.csci3130.fastmoney.views;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import ca.dal.cs.csci3130.fastmoney.R;

public class LandingPageActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        firebaseAuth=FirebaseAuth.getInstance();

        //handler for button
        TextView signOutLink = findViewById(R.id.signOutLink);
        signOutLink.setOnClickListener(this);
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

    public void redirectToJobPage(View view) {
        Intent redirect = new Intent(this, JobActivity.class);
        startActivity(redirect);
    }

    public void redirectToPostJobPage(View view) {
        Intent redirect = new Intent(this, AddJobActivity.class);
        startActivity(redirect);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.signOutLink){
            firebaseAuth.signOut();
            redirectToLogInPage(view);
        }
    }
}
