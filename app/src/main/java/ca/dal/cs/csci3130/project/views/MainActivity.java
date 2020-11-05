package ca.dal.cs.csci3130.project.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ca.dal.cs.csci3130.project.R;
import ca.dal.cs.csci3130.project.registration.RegistrationValidator;
import ca.dal.cs.csci3130.project.models.Account;

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
    }

    /**
     * Creates a user from the information given in the registration form.
     */
    public void submitForm(View view) {

        // Gets registration form's values for usernames.
        TextInputEditText usernameField = (TextInputEditText)this.findViewById(R.id.username_field);
        TextInputEditText emailField = (TextInputEditText)this.findViewById(R.id.email_field);
        final String username = usernameField.getText().toString();
        String email = emailField.getText().toString();

        // Creates a validator object to verify the information given is good.
        RegistrationValidator validator = new RegistrationValidator();

        // Bad information, show errors.
        if (!validator.isValidUsername(username)) {
            TextView usernameError = (TextView)this.findViewById(R.id.username_error);
            usernameError.setVisibility(View.VISIBLE);

            if (validator.hasNonAlphanumericCharacters(username))
            {
                usernameError.setText(R.string.register_username_alphanumeric_error);
            }
            else if (validator.isMissingValue(username))
            {
                usernameError.setText(R.string.register_username_missing_error);
            }
            else {
                usernameError.setText(R.string.unknown_error);
            }
        }

        // Bad information shows errors.
        if (!validator.isValidEmail(email)) {
            TextView emailError = (TextView)this.findViewById(R.id.email_error);
            emailError.setVisibility(View.VISIBLE);

            if (validator.isMissingValue(email))
            {
                emailError.setText(R.string.register_email_missing_error);
            }
            else if (!validator.isValidEmailFormat(email))
            {
                emailError.setText(R.string.register_email_format_error);
            }
            else {
                emailError.setText(R.string.unknown_error);
            }
        }

        // Good information sign-in.
        if (validator.isValidUsername(username) && validator.isValidEmail(email)) {

            final Account user = new Account(username, email);
            final String givenUsername = username;
            final String givenEmail = email;

            // Create the pop-up
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // Attempt to sign in with information
            firebaseAuth.signInWithEmailAndPassword(email, username)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // Database already has the account.
                            if (task.isSuccessful()) {
                                TextView existingAccountError = findViewById(R.id.existingAccountError);
                                existingAccountError.setVisibility(View.VISIBLE);
                                firebaseAuth.signOut();
                            // New user, save them into database.
                            } else {
                                firebaseAuth.createUserWithEmailAndPassword(givenEmail, givenUsername);

                                // Show welcome message.
                                builder.setTitle("Welcome " + user.getUsername() + "!")
                                        .setMessage("A welcome email was sent to " + user.getEmail())
                                        .show();
                            }
                        }
                    });
        }
    }
}
