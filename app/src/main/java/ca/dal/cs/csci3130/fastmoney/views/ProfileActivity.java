package ca.dal.cs.csci3130.fastmoney.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import ca.dal.cs.csci3130.fastmoney.R;

public class ProfileActivity extends AppCompatActivity {
    String username;
    String email;
    String ccNum;
    boolean editing = false;
    Button editbtn;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();

    DocumentReference docRef = db.collection("users").document((String)fAuth.getCurrentUser().getUid());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView usernameLabel = (TextView)findViewById(R.id.usernameTxtView);
        final EditText emailInput = (EditText)findViewById(R.id.emailInput);
        emailInput.setFocusable(false);

        editbtn = (Button)findViewById(R.id.editBtn);

        editbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(editing){

                } else {
                    editing = true;
                    editbtn.setText("Save");
                    emailInput.setFocusable(true);
                }
            }
        });

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Map<String, Object> data = document.getData();
                        username = (String)data.get("firstName") + " " + (String)data.get("lastName");
                        email = (String)data.get("email");
                        ccNum = (String)data.get("creditCardNum");
                        //display user data
                        usernameLabel.setText(username);
                        emailInput.setText(email);
                    } else {
                        //error handling for if user does not exist
                    }
                } else {
                    //error handing for if something went wrong when attempting to query the database
                }
            }
        });

    }
}
