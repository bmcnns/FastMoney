package ca.dal.cs.csci3130.fastmoney.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ca.dal.cs.csci3130.fastmoney.R;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        fAuth= FirebaseAuth.getInstance();

        //handler for button
        Button signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);

        Button registrationButton = findViewById(R.id.regButton);
        registrationButton.setOnClickListener(this);
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

    //show error messages for unsuccessful log ins
    protected void setStatusMessage(String message){
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }

    //checks to make sure the email and password are in a valid format
    public boolean checkForValidSignIn(String email, String password){
        //check for empty email or password
        if(email.isEmpty() || password.isEmpty()){
            setStatusMessage("Empty email or password");
            return false;
        }
        //check for invalid email
        else if(!email.contains("@") || !email.contains(".")){
            setStatusMessage("Invalid email or password");
            return false;
        }
        return true;
    }


    public void onClick(View view) {
        //redirects to the registration page
        if (view.getId() == R.id.regButton) {
            redirectRegistrationPage();
        }

        //checks for valid sign in on button click
        else if (view.getId() == R.id.signInButton) {
            String email = getEmailInput();
            String password = getPasswordInput();

            //ensures valid login credentials before continuing
            if (checkForValidSignIn(email, password)) {
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            redirectLandingPage();
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            setStatusMessage("Incorrect email or password");
                        }
                    }
                });
            }
        }
    }

    //redirects user to the main activity
    public void redirectLandingPage(){
        Intent Redirect = new Intent(this, LandingPageActivity.class);
        startActivity(Redirect);
    }

    //redirect to the registration page
    public void redirectRegistrationPage(){
        Intent Redirect = new Intent(this, RegistrationActivity.class);
        startActivity(Redirect);
    }
}
