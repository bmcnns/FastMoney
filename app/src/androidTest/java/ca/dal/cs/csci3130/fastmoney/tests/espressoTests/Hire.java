package ca.dal.cs.csci3130.fastmoney.tests.espressoTests;

import android.content.Context;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.views.HireActivity;
import ca.dal.cs.csci3130.fastmoney.views.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class Hire {

    @Rule
    public ActivityScenarioRule<HireActivity> myRule = new ActivityScenarioRule<>(HireActivity.class);

    @Before
    public void setUp() {
        HireActivity.TEST_MODE = "";
    }

    @Test
    public void showsFilledJobs() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Your Filled Jobs")).perform(scrollTo());
        onView(withText("Your Filled Jobs")).check(matches(isDisplayed()));
    }

    @Test
    public void showsUnfilledJobs() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Your Unfilled Jobs")).perform(scrollTo());
        onView(withText("Your Unfilled Jobs")).check(matches(isDisplayed()));
    }

    @Test
    public void showsNoJobsWhenNoJobs() {
        HireActivity.TEST_MODE = "NO_JOBS";
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText("You haven't posted any jobs.")).check(matches(isDisplayed()));
    }

    @Test
    public void showsPostAJobButtonWhenNoJobs() {
        HireActivity.TEST_MODE = "NO_JOBS";

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Post Job")).check(matches(isDisplayed()));
    }
}