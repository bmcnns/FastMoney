package ca.dal.cs.csci3130.fastmoney.views;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.fragments.JobCard;

public class HireActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public static String TEST_MODE = null;

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
                            if (task.getResult().size() == 0 || TEST_MODE.equals("NO_JOBS")) {
                                LinearLayout noJobsContainer = findViewById(R.id.hire_noJobsContainer);
                                noJobsContainer.setVisibility(View.VISIBLE);
                                LinearLayout filledJobsContainer = findViewById(R.id.hire_filledJobs_rootNode);
                                filledJobsContainer.setVisibility(View.GONE);
                                LinearLayout unfilledJobsContainer = findViewById(R.id.hire_unfilledJobs_rootNode);
                                unfilledJobsContainer.setVisibility(View.GONE);
                            }
                            else {
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
                    }
                });
    }
}
