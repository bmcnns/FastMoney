package ca.dal.cs.csci3130.fastmoney.views;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.fragments.JobCard;

public class HireActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire);

        firebaseFirestore.collection("jobs")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (!task.isSuccessful()) {
                        } else {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                JobCard jobCard = JobCard.newInstance(document.getId());
                                fragmentTransaction.add(R.id.hire_filledJobs_rootNode, jobCard, document.getId());
                                JobCard applicationCard = JobCard.newInstance(document.getId());
                                fragmentTransaction.add(R.id.hire_unfilledJobs_rootNode, applicationCard, document.getId());
                                fragmentTransaction.commit();
                            }
                        }
                    }
                });
    }
}
