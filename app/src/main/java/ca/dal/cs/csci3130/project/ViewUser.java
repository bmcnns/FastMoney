package ca.dal.cs.csci3130.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.Map;

public class ViewUser extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //change document later to use intent or shared preferences
    DocumentReference docRef = db.collection("users").document("txRupdjJVTY8zwkFhs2A");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //set labels
                        Map<String, Object> data = document.getData();
                        TextView fNameLabel = (TextView)findViewById(R.id.userFname);
                        TextView lNameLabel = (TextView)findViewById(R.id.userLname);
                        fNameLabel.setText((String)data.get("fName"));
                        lNameLabel.setText((String)data.get("lName"));
                    } else {
                        //error
                    }
                } else {
                    //error
                }
            }
        });
    }
}