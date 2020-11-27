package ca.dal.cs.csci3130.fastmoney.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.models.Employee;
import ca.dal.cs.csci3130.fastmoney.models.Job;

public class EmployeesActivity extends AppCompatActivity {
    /**
     * Initializes the app. Main entry point.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);

        Employee employee = new Employee(
                "Bryce",
                "MacInnis",
                "Hey my name is Bryce MacInnis. I am a top notch programmer and I eat cereal for supper."
        );

        Employee employee2 = new Employee(
                "John",
                "O'Reilly",
                "Hey my name is John O'Reilly. I am a bottom notch programmer and I eat cereal for breakfast."
        );

        //addEmployeeToDatabase(employee);
        //addEmployeeToDatabase(employee2);

        displayEmployeesFromDatabase();
    }

    public void addEmployeeToDatabase(Employee employee) {
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("firstName", employee.getFirstName());
        payload.put("lastName", employee.getLastName());
        payload.put("bio", employee.getBio());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String randomEmployeeId = UUID.randomUUID().toString();
        db.collection("employees").document(randomEmployeeId).set(payload);
    }

    public List<Job> displayEmployeesFromDatabase() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("employees").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<Job> result = new ArrayList<>();

                    for (QueryDocumentSnapshot employee : task.getResult()) {
                        Map<String, Object> employeeData = employee.getData();

                        Employee retrieved = new Employee(
                                (String) employeeData.get("firstName"),
                                (String) employeeData.get("lastName"),
                                (String) employeeData.get("bio")
                        );

                        displayEmployee(retrieved);
                    }
                }
            }
        });

        return null;
    }

    public void displayEmployee(Employee employee) {
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.activity_employee, null);

        // fill in any details dynamically here
        TextView employeeNameText = (TextView) v.findViewById(R.id.employeeName);
        employeeNameText.setText(employee.getName());

        TextView employeeBioText = (TextView) v.findViewById(R.id.employeeBio);
        employeeBioText.setText(employee.getBio());

        ViewGroup parent = (ViewGroup) findViewById(R.id.employeesRoot);
        parent.addView(v);
    }
}