package ca.dal.cs.csci3130.fastmoney.views;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

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

public class WorkActivity extends AppCompatActivity {
    public static String TEST_MODE;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        firebaseFirestore.collection("jobs")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (!task.isSuccessful()) {
                        } else {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (task.getResult().size() == 0 || WorkActivity.TEST_MODE == "NO_JOBS") {
                                    ((LinearLayout)findViewById(R.id.work_noJobsContainer)).setVisibility(View.VISIBLE);
                                    ((LinearLayout)findViewById(R.id.work_currentJobs_rootNode)).setVisibility(View.GONE);
                                    ((LinearLayout)findViewById(R.id.work_applications_rootNode)).setVisibility(View.GONE);
                                }
                                else {
                                    FragmentManager fm = getFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    JobCard jobCard = JobCard.newInstance(document.getId());
                                    fragmentTransaction.add(R.id.work_currentJobs_rootNode, jobCard, document.getId());
                                    JobCard applicationCard = JobCard.newInstance(document.getId());
                                    fragmentTransaction.add(R.id.work_applications_rootNode, applicationCard, document.getId());
                                    fragmentTransaction.commit();
                                }
                            }
                        }
                    }
                });
    }
}
