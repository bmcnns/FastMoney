package ca.dal.cs.csci3130.fastmoney.views;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.fragments.JobCard;

public class FindJobActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_job);

        final FindJobActivity instance = this;
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
                                fragmentTransaction.add(R.id.findJob_rootNode, jobCard, document.getId());
                                fragmentTransaction.commit();
                            }
                        }
                    }
                });
    }


}
