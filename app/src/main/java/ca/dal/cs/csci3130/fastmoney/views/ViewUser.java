package ca.dal.cs.csci3130.fastmoney.views;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import ca.dal.cs.csci3130.fastmoney.R;

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
                        TextView usernameLabel = (TextView)findViewById(R.id.userUsername);
                        TextView cityTitleLabel = (TextView)findViewById(R.id.cityTitle);
                        TextView userCityLabel = (TextView)findViewById(R.id.userCity);
                        TextView userBioLabel = (TextView)findViewById(R.id.userBio);
                        TextView userEmailLabel = (TextView)findViewById(R.id.userEmail);
                        TextView userPhoneLabel = (TextView)findViewById(R.id.userPhone);
                        fNameLabel.setText((String)data.get("fName"));
                        lNameLabel.setText((String)data.get("lName"));
                        usernameLabel.setText((String)data.get("username"));
                        if(((String)data.get("userType")).equals("employer")){
                            cityTitleLabel.setText("Looking for workers in: ");
                        } else {
                            cityTitleLabel.setText("Looking for work in: ");
                        }
                        userCityLabel.setText((String)data.get("city"));
                        userBioLabel.setText((String)data.get("bio"));
                        userEmailLabel.setText((String)data.get("email"));
                        userPhoneLabel.setText((String)data.get("phoneNum"));
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