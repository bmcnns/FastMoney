package ca.dal.cs.csci3130.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void submitForm(View view) {
        TextInputEditText usernameField = (TextInputEditText)this.findViewById(R.id.username_field);
        TextInputEditText emailField = (TextInputEditText)this.findViewById(R.id.email_field);
        final String username = usernameField.getText().toString();
        String email = emailField.getText().toString();

        RegistrationValidator validator = new RegistrationValidator();

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

        if (validator.isValidUsername(username) && validator.isValidEmail(email)) {

            final User user = new User(username, email);
            final String givenUsername = username;
            final String givenEmail = email;

            // Create the pop-up
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            firebaseAuth.signInWithEmailAndPassword(email, username)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                TextView existingAccountError = findViewById(R.id.existingAccountError);
                                existingAccountError.setVisibility(View.VISIBLE);
                                firebaseAuth.signOut();
                            } else {
                                firebaseAuth.createUserWithEmailAndPassword(givenEmail, givenUsername);

                                builder.setTitle("Welcome " + user.getUsername() + "!")
                                        .setMessage("A welcome email was sent to " + user.getEmail())
                                        .show();
                            }
                        }
                    });
        }
    }
}
