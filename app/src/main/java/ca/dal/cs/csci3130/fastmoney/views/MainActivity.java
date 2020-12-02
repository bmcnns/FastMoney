package ca.dal.cs.csci3130.fastmoney.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import ca.dal.cs.csci3130.fastmoney.R;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    /**
     * Initializes the app. Main entry point.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        Intent redirect;
        redirect = new Intent(this, LogInActivity.class);
        startActivity(redirect);
        finish();
    }

}
