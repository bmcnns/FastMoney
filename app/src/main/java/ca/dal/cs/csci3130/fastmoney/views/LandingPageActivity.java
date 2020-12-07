package ca.dal.cs.csci3130.fastmoney.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.Map;

import ca.dal.cs.csci3130.fastmoney.R;

public class LandingPageActivity extends AppCompatActivity {
    public static String TEST_MODE;

    String fName, lName;

    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String currentUser = (fAuth.getCurrentUser() == null) ? "testEmployer" : fAuth.getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        final TextView welcomeMessage = (TextView)findViewById(R.id.welcomeHeader);
        final TextView signOutLink = (TextView)findViewById(R.id.signOutLink);

        db.collection("users").document(currentUser)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    private static final String TAG = "db";

                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Map<String, Object> data = document.getData();
                                fName = (String) data.get("firstName");
                                lName = (String) data.get("lastName");
                                welcomeMessage.setText("Welcome, " + fName);
                                signOutLink.setText("Not " + fName + "? Sign out.");
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
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
