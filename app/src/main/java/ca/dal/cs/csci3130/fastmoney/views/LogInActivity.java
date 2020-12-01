package ca.dal.cs.csci3130.fastmoney.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import ca.dal.cs.csci3130.fastmoney.R;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        firebaseAuth= FirebaseAuth.getInstance();

        //handler for button
        Button signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);

        Button registerButton = findViewById(R.id.registerButton);
        signInButton.setOnClickListener(this);
    }


    //gets the inputted email
    protected String getEmailInput(){
        EditText emailBox= findViewById(R.id.EmailAddress);
        return emailBox.getText().toString();
    }

    //gets the inputted password
    protected String getPasswordInput(){
        EditText passwordBox = findViewById(R.id.Password);
        return passwordBox.getText().toString();
    }

    //sets error message on invalid login
    protected void setStatusMessage(String message){
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }

    //checks to make sure the email and password are in a valid format
    public boolean checkForValidSignIn(String email, String password){
        //check for empty email or password
        if(email.isEmpty() || password.isEmpty()){
            setStatusMessage("Empty username or password");
            return false;
        }
        //check for invalid email
        else if(!email.contains("@") || !email.contains(".")){
            setStatusMessage("Invalid username or password");
            return false;
        }
        return true;
    }


    public void onClick(View view){
        //redirects when register button clicked
        if(view.getId()==R.id.registerButton){
            redirectRegistrationPage();
        }


        else if(view.getId()==R.id.signInButton) {
            String email = getEmailInput();
            String password = getPasswordInput();

            if (checkForValidSignIn(email, password)) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("", "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            redirectLandingPage();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("", "signInWithEmail:failure", task.getException());
                            setStatusMessage("Incorrect username or password");
                        }
                    }
                });
            }
        }
    }
    public void redirectRegistrationPage(){
       // Intent Redirect = new Intent(this, RegistrationActivity.class);
        //startActivity(Redirect);
    }

    public void redirectLandingPage(){
        Intent Redirect = new Intent(this, MainActivity.class);
        startActivity(Redirect);
    }
}
