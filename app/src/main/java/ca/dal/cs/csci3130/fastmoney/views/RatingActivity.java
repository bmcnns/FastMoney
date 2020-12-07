package ca.dal.cs.csci3130.fastmoney.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import ca.dal.cs.csci3130.fastmoney.R;

public class RatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
    }

    private int extractRating() {
        int result = 0;
        ArrayList<View> stars = new ArrayList<>();
        LinearLayout starContainer = findViewById(R.id.rating_starContainer);
        int starCount = starContainer.getChildCount();
        for (int i = 0; i < starCount; i++) {
            View v = starContainer.getChildAt(i);
            if (v instanceof ImageView) {
                if (v.getTag() != null && v.getTag().equals("filled")) {
                    result++;
                }
            }
        }

        return result;
    }

    private String extractReview() {
        TextInputEditText reviewText = findViewById(R.id.rating_reviewInput);
        return reviewText.getText().toString();
    }

    private boolean isFormValid() {
        int rating = extractRating();
        String review = extractReview();

        if (rating < 1 && rating > 5)
            return false;
        else
            return true;
    }

    public void rate(View star) {
        int result = 0;
        ArrayList<View> stars = new ArrayList<>();
        LinearLayout starContainer = findViewById(R.id.rating_starContainer);
        int starCount = starContainer.getChildCount();
        for (int i = 0; i < starCount; i++) {
            View v = starContainer.getChildAt(i);
            if (v instanceof ImageView) {
                if (v.equals(star)) {
                    for (int j = i; j >= 0; j--) {
                        if (starContainer.getChildAt(j) instanceof ImageView == false)
                            continue;
                        else {
                            starContainer.getChildAt(j).setTag("filled");
                            ImageView starImage = (ImageView)starContainer.getChildAt(j);
                            starImage.setImageResource(android.R.drawable.star_big_on);
                        }
                    }
                    for (int j = i+1; j < starCount; j++) {
                        if (starContainer.getChildAt(j) instanceof ImageView == false)
                            continue;
                        else {
                            starContainer.getChildAt(j).setTag("unfilled");
                            ImageView starImage = (ImageView)starContainer.getChildAt(j);
                            starImage.setImageResource(android.R.drawable.star_big_off);
                        }
                    }

                    break;
                }
            }
        }
    }

    private void showErrorMessage() {
        TextView ratingError = findViewById(R.id.rating_ratingError);
        ratingError.setVisibility(View.VISIBLE);
    }

    public void submit(View v) {
        if (isFormValid()) {
            Intent redirect = new Intent(this, LandingPageActivity.class);
            startActivity(redirect);
            finish();
        }
        else {
            showErrorMessage();
        }
    }

}