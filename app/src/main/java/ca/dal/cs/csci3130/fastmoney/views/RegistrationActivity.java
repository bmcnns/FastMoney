package ca.dal.cs.csci3130.fastmoney.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import ca.dal.cs.csci3130.fastmoney.R;

public class RegistrationActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button registerBtn;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        registerBtn = findViewById(R.id.registerRGBtn);
        loginBtn = findViewById(R.id.loginRGBtn);
        final TextInputEditText emailField = this.findViewById(R.id.email_field);
        final TextInputEditText passwordField = this.findViewById(R.id.password_field);
        final TextInputEditText firstNameField = this.findViewById(R.id.first_name_field);
        final TextInputEditText lastNameField = this.findViewById(R.id.last_name_field);
//        final EditText creditCardNumField = this.findViewById(R.id.credit_card_Num_field);
//        final EditText creditCardExpireField = this.findViewById(R.id.credit_card_expire_field);
//        final EditText cvvField = this.findViewById(R.id.cvv_field);


        /**
         * Creates a user from the information given in the registration form.
         */
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = emailField.getText().toString();
                final String password = passwordField.getText().toString();
                final String firstName = firstNameField.getText().toString();
                final String lastName = lastNameField.getText().toString();
//                final String creditCardNum = creditCardNumField.getText().toString();
//                final String creditCardExpire = creditCardExpireField.getText().toString();
//                final String ccv = cvvField.getText().toString();

                //if user is already logged in
                if (fAuth.getCurrentUser() != null) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }

                //Registration with firebase authentication and store data to firebase firestone

                fAuth.createUserWithEmailAndPassword(email, password).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String userID = fAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = fStore.collection("users").document(userID);
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("email", email);
                                    user.put("password", password);
                                    user.put("firstName", firstName);
                                    user.put("lastName", lastName);
                                    user.put("creditCardNum", 1);
                                    user.put("creditCardExpire", 1);
                                    user.put("ccv", 1);


                                    /** Store extra User data to firestone once successful registration to firebase authentication
                                     * */

                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("", "user have been registered");
                                            Toast.makeText(RegistrationActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        }

                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegistrationActivity.this, "Error" + e, Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    /* Jump to new Activity*/
                                } else {
                                    Toast.makeText(RegistrationActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }


        });


        /* Login button to the login Page*/
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LogInActivity.class));
            }
        });
    }
}
