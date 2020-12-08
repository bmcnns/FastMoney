package ca.dal.cs.csci3130.fastmoney.views;

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
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.models.Job;
import ca.dal.cs.csci3130.fastmoney.models.User;
import ca.dal.cs.csci3130.fastmoney.testing.TestingController;
import ca.dal.cs.csci3130.fastmoney.testing.TestingMode;

public class ProfileActivity extends AppCompatActivity {
    static boolean isEditing = false;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();

    String currentUserId = (fAuth.getCurrentUser() != null) ? fAuth.getCurrentUser().getUid() : "test";
    DocumentReference currentUserDocument = db.collection("users").document(currentUserId);
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TestingController.setTestingMode(TestingMode.ENABLED);

        if (TestingController.getTestingMode() == TestingMode.DISABLED) {
            queryDatabaseForUserAndUpdateDisplay(currentUserDocument);
        }
        else {
            currentUser = TestingController.getTestUser();
            updateDisplayInformation(currentUser);
        }
    }

    private void queryDatabaseForUserAndUpdateDisplay(DocumentReference userDocument) {
        userDocument.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        currentUser = getUserFromData(document.getData());
                        updateDisplayInformation(currentUser);
                    }
                }
            }
        });
    }

    private User getUserFromData(Map<String, Object> data) {
        return new User(
                (String)data.get("firstName"),
                (String)data.get("lastName"),
                (String)data.get("email"),
                (String)data.get("location"),
                "",
                (String)data.get("image"),
                (long)data.get("employeeRating"),
                (long)data.get("employerRating")
        );
    }

    private void updateRatingDisplay(LinearLayout container, int value) {
        for(int i = 0; i < value; i++){
            ((ImageView)container.getChildAt(i)).setVisibility(View.VISIBLE);
        }
    }

    private void updateDisplayInformation(User user) {
        ((TextView)(findViewById(R.id.profile_fullName))).setText(user.getFullName());
        ((TextView)(findViewById(R.id.profile_firstNameInput))).setText(user.getFirstName());
        ((TextView)(findViewById(R.id.profile_lastNameInput))).setText(user.getLastName());
        ((TextView)(findViewById(R.id.profile_location))).setText(user.getLocation());
        ((TextView)(findViewById(R.id.profile_emailInput))).setText(user.getEmail());

        updateRatingDisplay(
                (LinearLayout)findViewById(R.id.profile_employerRating),
                (int)Math.floor(user.getEmployerRating())
        );

        updateRatingDisplay(
                (LinearLayout)findViewById(R.id.profile_employeeRating),
                (int)Math.floor(user.getEmployeeRating())
        );
    }

    private User extractUserFromForm() {
        String firstName = ((TextView)findViewById(R.id.profile_firstNameInput)).getText().toString();
        String lastName = ((TextView)findViewById(R.id.profile_lastNameInput)).getText().toString();
        String email = ((TextView)findViewById(R.id.profile_emailInput)).getText().toString();

        User updatedUser = new User(
                firstName,
                lastName,
                email,
                currentUser.getLocation(),
                currentUser.getCreditCard(),
                currentUser.getImage(),
                currentUser.getEmployerRating(),
                currentUser.getEmployeeRating()
        );

        if (User.isValid(updatedUser))
            return updatedUser;
        else
            return null;
    }

    private void updateUserInDatabase(DocumentReference userDocument, final User user) {
        final Map<String, Object> editedUserData = new HashMap<>();
        editedUserData.put("firstName", user.getFirstName());
        editedUserData.put("lastName", user.getLastName());
        editedUserData.put("email", user.getEmail());

        userDocument.update(editedUserData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                stopEditing();
            }
        });
    }

    private void stopEditing() {
        ProfileActivity.isEditing = false;
        ((MaterialButton)findViewById(R.id.profile_editButton)).setText("Edit");
        ((Button)findViewById(R.id.profile_cancelButton)).setVisibility(View.INVISIBLE);
        ((LinearLayout)findViewById(R.id.profile_editNameContainer)).setVisibility(View.INVISIBLE);
        ((TextView)findViewById(R.id.profile_fullName)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.profile_emailInput)).setFocusable(false);
        ((TextView)findViewById(R.id.profile_firstNameInput)).setText(currentUser.getFirstName());
        ((TextView)findViewById(R.id.profile_lastNameInput)).setText(currentUser.getLastName());
    }

    private void startEditing() {
        ProfileActivity.isEditing = true;
        ((MaterialButton)findViewById(R.id.profile_editButton)).setText("Save");
        ((TextView)findViewById(R.id.profile_fullName)).setVisibility(View.INVISIBLE);
        ((Button)findViewById(R.id.profile_cancelButton)).setVisibility(View.VISIBLE);
        ((LinearLayout)findViewById(R.id.profile_editNameContainer)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.profile_emailInput)).setFocusableInTouchMode(true);
        ((TextView)findViewById(R.id.profile_emailInput)).setFocusable(true);
    }

    private void signOutUserAndRedirect() {
        fAuth.signOut();
        Intent redirect = new Intent(this, LogInActivity.class);
        startActivity(redirect);
    }

    public void toggleEditing(View v) {
        if (ProfileActivity.isEditing) {
            User editedUser = extractUserFromForm();

            if (User.isValid(editedUser)) {
                updateUserInDatabase(currentUserDocument, currentUser);
            }
        } else {
            startEditing();
        }
    }

    public void deleteAccount(View v) {
        currentUserDocument.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void v) {
                db.collection("users").document(currentUserId).delete();
                fAuth.getCurrentUser().delete();
                signOutUserAndRedirect();
            }
        });
    }

    public void workHistoryRedirect(View v){
        Intent redirect = new Intent(this, WorkHistoryActivity.class);
        startActivity(redirect);
    }

    public void signOut(View view) {
        signOutUserAndRedirect();
    }
}
