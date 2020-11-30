package ca.dal.cs.csci3130.fastmoney.examples.espressoTests;

import android.content.Context;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.views.AddJobActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AddJob {

    @Rule
    public ActivityScenarioRule<AddJobActivity> myRule = new ActivityScenarioRule<>(AddJobActivity.class);

    @Test
    public void showsJobTitleInput() {
        onView(withText("Job Title")).check(matches(isDisplayed()));
        onView(withHint("John")).check(matches(isDisplayed()));
    }

    @Test
    public void showsPayRateInput() {
        onView(withText("Pay Rate")).check(matches(isDisplayed()));
        onView(withHint("$0/hr")).check(matches(isDisplayed()));
    }

    @Test
    public void showsJobDescriptionInput() {
        onView(withText("Job Description")).check(matches(isDisplayed()));
        onView(withHint("Raking and disposing of leaves...")).check(matches(isDisplayed()));
    }

    @Test
    public void showsImagesInput() {
        onView(withText("Images")).check(matches(isDisplayed()));
    }

    @Test
    public void showsPostButton() {
        onView(withText("Post")).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenTitleIsNotAdded() {
        onView(withText("Post")).perform(click());
        onView(withText("Title can not be blank")).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenTitleIsOverMaxLength() {
        String absurdlyLargeString = "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww";
        onView(withText("John")).perform(typeText(absurdlyLargeString));
        onView(withText("Post")).perform(click());
        onView(withText("Title must be fewer characters")).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenPayRateIsNotAdded() {
        onView(withText("$0/hr")).perform(clearText());
        onView(withText("Post")).perform(click());
        onView(withText("Pay rate can not be blank")).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenPayRateIsLowerThanMinimumValue() {
        String minimumPayRate = "$1/hr";
        onView(withText("$0/hr")).perform(clearText());
        onView(withText("Post")).perform(click());
        onView(withText("Pay rate must be more than minimum value.")).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenPayRateIsLowerThanMaximumValue() {
        String maximumPayRate = "$1000/hr";
        onView(withText("$0/hr")).perform(clearText());
        onView(withText("$0/hr")).perform(typeText(maximumPayRate));
        onView(withText("Post")).perform(click());
        onView(withText("Pay rate must be less than the maximum value.")).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenDescriptionIsNotAdded() {
        onView(withText("Raking and disposing of leaves...")).perform(clearText());
        onView(withText("Post")).perform(click());
        onView(withText("Description must be added.")).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenDescriptionIsNotValidLength() {
        String absurdlyLargeString = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        onView(withText("Raking and disposing of leaves...")).perform(clearText());
        onView(withText("Raking and disposing of leaves...")).perform(typeText(absurdlyLargeString));
        onView(withText("Post")).perform(click());
        onView(withText("Description must be less than maximum length.")).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenMinimumAmountOfImagesNotIncluded() {
        assertFalse(!false);
    }

    @Test
    public void showsErrorWhenMaximumAmountOfImagesExceeded() {
        assertFalse(!false);
    }
}