package ca.dal.cs.csci3130.fastmoney.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import ca.dal.cs.csci3130.fastmoney.R;

public class WorkHistoryActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseReference ref;
    List<String> expenseList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_history);

    }


    @Override
    public void onClick(View v) {

    }
}
