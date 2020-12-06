package ca.dal.cs.csci3130.fastmoney.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import ca.dal.cs.csci3130.fastmoney.R;

public class ProfileActivity extends AppCompatActivity {
    String fName;
    String lName;
    String email;
    long ccNum;
    String location;
    long employeeRating;
    long employerRating;
    boolean editing = false;
    Button editbtn;
    Button cancelBtn;
    Button signOutBtn;
    Button deleteAccBtn;
    LinearLayout editNameLayout;
    Button workHistoryBtn;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();

    DocumentReference docRef = db.collection("users").document((String)fAuth.getCurrentUser().getUid());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView usernameLabel = (TextView)findViewById(R.id.usernameTxtView);
        final TextView locationLabel = (TextView)findViewById(R.id.userLocationTxtView);
        final EditText emailInput = (EditText)findViewById(R.id.emailInput);
        final EditText fNameInput = (EditText)findViewById(R.id.fNameInput);
        final EditText lNameInput = (EditText)findViewById(R.id.lNameInput);
        emailInput.setFocusable(false);

        editNameLayout = (LinearLayout)findViewById(R.id.editNameLayout);
        editbtn = (Button)findViewById(R.id.editBtn);
        cancelBtn = (Button)findViewById(R.id.cancelBtn);
        signOutBtn = (Button)findViewById(R.id.signOutBtn);
        deleteAccBtn = (Button)findViewById(R.id.deleteAccountBtn);
        workHistoryBtn = (Button)findViewById(R.id.workHistoryButton);

        deleteAccBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                docRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        db.collection("users").document(fAuth.getUid()).delete();
                        fAuth.getCurrentUser().delete();
                        signOut();
                    }
                });
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        workHistoryBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                redirectWorkHistoryPage();
            }
        });

        editbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(editing){
                    final Map<String, Object> newUserData = new HashMap<>();
                    newUserData.put("firstName", String.valueOf(fNameInput.getText()));
                    newUserData.put("lastName", String.valueOf(lNameInput.getText()));
                    newUserData.put("email", String.valueOf(emailInput.getText()));
                    email=String.valueOf(emailInput.getText());
                    docRef.update(newUserData).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            editing = false;
                            editbtn.setText("Edit");
                            cancelBtn.setVisibility(View.INVISIBLE);
                            editNameLayout.setVisibility(View.INVISIBLE);
                            usernameLabel.setVisibility(View.VISIBLE);
                            emailInput.setFocusable(false);
                            String username = newUserData.get("firstName") + " " + newUserData.get("lastName");
                            fName = (String)newUserData.get("firstName");
                            lName = (String)newUserData.get("lastName");
                            usernameLabel.setText(username);
                            fNameInput.setText(fName);
                            lNameInput.setText(lName);
                            emailInput.setText(email);
                        }
                    });
                } else {
                    editing = true;
                    editbtn.setText("Save");
                    usernameLabel.setVisibility(View.INVISIBLE);
                    cancelBtn.setVisibility(View.VISIBLE);
                    editNameLayout.setVisibility(View.VISIBLE);
                    emailInput.setFocusableInTouchMode(true);
                    emailInput.setFocusable(true);
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                editing = false;
                editbtn.setText("Edit");
                cancelBtn.setVisibility(View.INVISIBLE);
                editNameLayout.setVisibility(View.INVISIBLE);
                usernameLabel.setVisibility(View.VISIBLE);
                emailInput.setFocusable(false);
                fNameInput.setText(fName);
                lNameInput.setText(lName);
            }
        });

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Map<String, Object> data = document.getData();
                        fName = (String)data.get("firstName");
                        lName = (String)data.get("lastName");
                        String username = fName + " " + lName;
                        email = (String)data.get("email");
                        ccNum = (long)data.get("creditCardNum");
                        location = (String)data.get("location");
                        employeeRating = (long)data.get("employeeRating");
                        employerRating = (long)data.get("employerRating");
                        //display user data
                        usernameLabel.setText(username);
                        fNameInput.setText(fName);
                        lNameInput.setText(lName);
                        locationLabel.setText(location);
                        emailInput.setText(email);

                        LinearLayout employeeRatingLayout = (LinearLayout)findViewById(R.id.employeeStars);
                        for(int i = 0; i < employeeRating; i++){
                            ImageView star = (ImageView)employeeRatingLayout.getChildAt(i);
                            star.setVisibility(View.VISIBLE);
                        }
                        LinearLayout employerRatingLayout = (LinearLayout)findViewById(R.id.employerStars);
                        for(int i = 0; i < employerRating; i++){
                            ImageView star = (ImageView)employerRatingLayout.getChildAt(i);
                            star.setVisibility(View.VISIBLE);
                        }
                    } else {
                        //error handling for if user does not exist
                    }
                } else {
                    //error handing for if something went wrong when attempting to query the database
                }
            }
        });

    }

    public void redirectWorkHistoryPage(){
        Intent redirect = new Intent(this, WorkHistoryActivity.class);
        startActivity(redirect);
    }

    public void signOut() {
        fAuth.signOut();
        Intent redirect = new Intent(this, LogInActivity.class);
        startActivity(redirect);
    }
}
