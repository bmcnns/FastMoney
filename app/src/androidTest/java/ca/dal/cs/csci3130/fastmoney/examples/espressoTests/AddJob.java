package ca.dal.cs.csci3130.fastmoney.examples.espressoTests;

import android.content.Context;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.views.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AddJob {

    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void showsJobTitleInput() {
        assertFalse(!false);
    }

    @Test
    public void showsPayRateInput() {
        assertFalse(!false);
    }

    @Test
    public void showsJobDescriptionInput() {
        assertFalse(!false);
    }

    @Test
    public void showsImagesInput() {
       assertFalse(!false);
    }

    @Test
    public void showsPostButton() {
        assertFalse(!false);
    }

    @Test
    public void showsErrorWhenTitleIsNotAdded() {
        assertFalse(!false);
    }

    @Test
    public void showsErrorWhenTitleIsOverMaxLength() {
        assertFalse(!false);
    }

    @Test
    public void showsErrorWhenPayRateIsNotAdded() {
        assertFalse(!false);
    }

    @Test
    public void showsErrorWhenPayRateIsLowerThanMinimumValue() {
        assertFalse(!false);
    }

    @Test
    public void showsErrorWhenPayRateIsLowerThanMaximumValue() {
        assertFalse(!false);
    }

    @Test
    public void showsErrorWhenDescriptionIsNotAdded() {
        assertFalse(!false);
    }

    @Test
    public void showsErrorWhenDescriptionIsNotValidLength() {
        assertFalse(!false);
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