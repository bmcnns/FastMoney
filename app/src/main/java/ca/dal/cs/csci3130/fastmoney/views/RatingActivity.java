package ca.dal.cs.csci3130.fastmoney.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;
import java.util.ArrayList;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.models.Config;
import ca.dal.cs.csci3130.fastmoney.models.PaymentStatus;

public class RatingActivity extends AppCompatActivity {

    private static final int PAYPAL_REQUEST_CODE = 555;
    int amount = 10;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(intent);
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

    public void pay(View v){

        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount)),"CAD",
                "Purchase Service",PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
        startActivityForResult(intent,PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null) {
                    try {
                        String paymentDetails = confirmation.toJSONObject().toString();
                        Log.d("Details",paymentDetails);
                        startActivity(new Intent(this, PaymentStatus.class)
                                .putExtra("PaymentDetails",paymentDetails)
                                .putExtra("Amount",amount));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this,PayPalService.class));
        super.onDestroy();
    }

}