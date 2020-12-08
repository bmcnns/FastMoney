package ca.dal.cs.csci3130.fastmoney.tests.espressoTests;

import android.content.Context;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.views.MainActivity;
import ca.dal.cs.csci3130.fastmoney.views.RatingActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class Rating {

    @Rule
    public IntentsTestRule<RatingActivity> myRule = new IntentsTestRule<>(RatingActivity.class);

    @Test
    public void ratingMustBeSelected() {
        onView(withText("Review")).perform(scrollTo());
        onView(withText("Submit")).perform(scrollTo(), click());
        onView(withText("Error: you must provide a rating."));
    }

    @Test
    public void showsSubmitButton() {
        onView(withText("Review")).perform(scrollTo());
        onView(withText("Submit")).check(matches(isDisplayed()));
    }

    @Test
    public void showsRating() {
        onView(withText("Rating")).check(matches(isDisplayed()));
    }

    @Test
    public void showsReview() {
        onView(withText("Review")).check(matches(isDisplayed()));
    }

}