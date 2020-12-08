package ca.dal.cs.csci3130.fastmoney.tests.espressoTests;

import android.content.Context;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.views.HireActivity;
import ca.dal.cs.csci3130.fastmoney.views.MainActivity;
import ca.dal.cs.csci3130.fastmoney.views.WorkActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class Work {

    @Rule
    public IntentsTestRule<WorkActivity> myRule = new IntentsTestRule<>(WorkActivity.class);

    @Before
    public void setUp() {
        WorkActivity.TEST_MODE = "";
    }

    @Test
    public void showsJobsHiredFor() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Your Current Jobs")).perform(scrollTo());
        onView(withText("Your Current Jobs")).check(matches(isDisplayed()));
    }

    @Test
    public void showsJobsAppliedFor() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Your Applications")).perform(scrollTo());
        onView(withText("Your Applications")).check(matches(isDisplayed()));
    }

    @Test
    public void showsNoJobsWhenNoJobs() {
        WorkActivity.TEST_MODE = "NO_JOBS";
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText("You don't have any jobs currently.")).check(matches(isDisplayed()));
    }

    @Test
    public void showsFindAJobButtonWhenNoJobs() {
        WorkActivity.TEST_MODE = "NO_JOBS";

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Find Job")).check(matches(isDisplayed()));
    }
}