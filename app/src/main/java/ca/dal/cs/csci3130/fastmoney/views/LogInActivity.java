package ca.dal.cs.csci3130.fastmoney.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

import ca.dal.cs.csci3130.fastmoney.R;

public class LogInActivity extends AppCompatActivity {
    FirebaseAuth fAuth = FirebaseAuth.getInstance();

    public void tempLogin(View view) {
        Toast.makeText(LogInActivity.this, "Signing in...", Toast.LENGTH_SHORT).show();

        fAuth.signInWithEmailAndPassword("test@test.com", "testtest").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userID = fAuth.getCurrentUser().getUid();
                    Toast.makeText(LogInActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), LandingPageActivity.class));

                } else {
                    Toast.makeText(LogInActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }
}
