package ca.dal.cs.csci3130.fastmoney.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.models.Job;
import ca.dal.cs.csci3130.fastmoney.models.User;

import static com.google.common.io.Resources.getResource;

public class AddJobActivity extends AppCompatActivity {

    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
    }

    private Job unpostedJob = null;

    private void showFormErrors(boolean title, boolean payRate, boolean jobDescription, boolean images) {
        if (title) {
            ((LinearLayout)(this.findViewById(R.id.addJob_titleError))).setVisibility(View.VISIBLE);
        }

        if (payRate) {
            ((LinearLayout)(this.findViewById(R.id.addJob_payRateError))).setVisibility(View.VISIBLE);
        }

        if (jobDescription) {
            ((LinearLayout)(this.findViewById(R.id.addJob_descriptionError))).setVisibility(View.VISIBLE);
        }

        if (images) {
            ((LinearLayout)(this.findViewById(R.id.addJob_imagesError))).setVisibility(View.VISIBLE);
        }
    }

    private String extractTitleFromForm() {
        return ((TextView)(this.findViewById(R.id.addJob_titleInput))).getText().toString();
    }

    private int extractPayRateFromForm() {
        String payRateText = ((TextView)(this.findViewById(R.id.addJob_payRateInput))).getText().toString();
        return (payRateText.isEmpty()) ? -1 : (int)Integer.valueOf(payRateText);

    }

    private String extractDescriptionFromForm() {
        return ((TextView)(this.findViewById(R.id.addJob_descriptionInput))).getText().toString();
    }

    private Job createJobFromForm() {
        Job result = null;
        String title = extractTitleFromForm();
        int payRate = extractPayRateFromForm();
        String description = extractDescriptionFromForm();

        return new Job(
                title,
                payRate,
                description,
                new ArrayList<String>(),
                new User("John","Callinger","john.callinger@example.com","Halifax, NS","4724090012345678","profile.png",5.0f,5.0f),
                null
        );
    }

    private void updateJobFromForm() {
        String title = extractTitleFromForm();
        int payRate = extractPayRateFromForm();
        String description = extractDescriptionFromForm();
        List<String> images = unpostedJob.getImages();

        unpostedJob.setTitle(title);
        unpostedJob.setPayRate(payRate);
        unpostedJob.setDescription(description);
        unpostedJob.setImages(images);
    }

    private Job extractJobFromForm() {
        Job result = null;
        String title = extractTitleFromForm();
        int payRate = extractPayRateFromForm();
        String description = extractDescriptionFromForm();
        List<String> images = unpostedJob.getImages();

        result = new Job(
                title,
                payRate,
                description,
                images,
                new User("John","Callinger","john.callinger@example.com","Halifax, NS","4724090012345678","profile.png",5.0f,5.0f),
                null
        );

        return result;
    };

    public void addJobToDatabase(Job job) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", job.getTitle());
        payload.put("postedDate", job.getPostedDate());
        payload.put("payRate", job.getPayRate());
        payload.put("images", job.getImages());
        payload.put("employeeId", "");
        payload.put("employerId", job.getEmployer());
        payload.put("description", job.getDescription());

        final AddJobActivity instance = this;
        firebaseFirestore.collection("jobs").document(UUID.randomUUID().toString()).set(job).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent redirect = new Intent(instance, LandingPageActivity.class);
                startActivity(redirect);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 69 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            unpostedJob.addImage(picturePath);
            updateDisplayedImages();
        }
    }


    private void uploadImagesToFirebase(Job job) {
        String randomRootName = UUID.randomUUID().toString();
        List<String> oldImages = new ArrayList<>(job.getImages());

        for (String imagePath : oldImages) {
            uploadImageToFirebase(job, randomRootName, imagePath);
        }

        for (String oldImage : oldImages) {
            job.removeImage(oldImage);
        }
    }

    private void uploadImageToFirebase(Job job, String root, final String imagePath) {
        // Create a storage reference from our app
        StorageReference storageRef = firebaseStorage.getReference();
        String[] names = (imagePath.substring(0, imagePath.lastIndexOf('.'))).split("/");
        String newPath = root+"/"+names[names.length - 1];

        job.addImage(newPath);

        // Create a reference to "mountains.jpg"
        final StorageReference mountainsRef = storageRef.child(newPath + ".jpg");

        if(isStoragePermissionGranted()) {
            Bitmap bm = BitmapFactory.decodeFile(imagePath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

            UploadTask uploadTask = mountainsRef.putBytes(data);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                }
            });
        }
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    private void updateDisplayedImages() {
        TableLayout tl = (TableLayout) findViewById(R.id.addJob_imageDisplayer);
        tl.removeAllViews();

        int numberOfColumns = 3;

        int numberOfRows = (int)(Math.ceil(unpostedJob.getImages().size() / (float)numberOfColumns));

        int imgCount = 0;
        for (int i = 0; i < numberOfRows; i++) {

            TableRow row = new TableRow(this);
            row.setOrientation(TableRow.HORIZONTAL);

            TableRow.LayoutParams lp = new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT,
                    1.0f
            );

            row.setLayoutParams(lp);

            for (int j = 0; j < numberOfColumns; j++) {

                ImageView img = new ImageView(this);

                TableRow.LayoutParams imgLp = new TableRow.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        (1.0f/(float)numberOfColumns));
                imgLp.setMargins(4,4,4,4);

                img.setLayoutParams(imgLp);
                img.setAdjustViewBounds(true);
                img.setScaleType(ImageView.ScaleType.CENTER_CROP);

                if(isStoragePermissionGranted() && imgCount < unpostedJob.getImages().size()){
                    Bitmap bm = BitmapFactory.decodeFile(unpostedJob.getImages().get(imgCount));
                    img.setImageBitmap(bm);
                }

                row.addView(img);
                imgCount++;
            }

            tl.addView(row);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            updateDisplayedImages();
        }
    }

    public void pickImageFromGallery(View view) {
        if (unpostedJob == null)
            unpostedJob = createJobFromForm();

        Intent i = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 69);
    }

    public void showErrors(String title, int payRate, String description) {
        if (!Job.isTitleValid(title)) {
            showFormErrors(true, false, false, false);
        }

        if (!Job.isPayRateValid(payRate)) {
            showFormErrors(false, true, false, false);
        }

        if (!Job.isDescriptionValid(description)) {
            showFormErrors(false, false, true, false);
        }

        if (!Job.isImagesValid(unpostedJob.getImages())) {
            showFormErrors(false, false, false, true);
        }
    }

    public void postJob(View view) throws Exception {
        if (unpostedJob == null)
            unpostedJob = createJobFromForm();
        else
            updateJobFromForm();

        unpostedJob = extractJobFromForm();
        String title = extractTitleFromForm();
        int payRate = extractPayRateFromForm();
        String description = extractDescriptionFromForm();

        showErrors(title, payRate, description);

        if (Job.isValid(unpostedJob)) {
            uploadImagesToFirebase(unpostedJob);
            addJobToDatabase(unpostedJob);
        }

        else {
            showErrors(title, payRate, description);
        }
    }
}
