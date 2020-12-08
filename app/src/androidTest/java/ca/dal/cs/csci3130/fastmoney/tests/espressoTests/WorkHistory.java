package ca.dal.cs.csci3130.fastmoney.tests.espressoTests;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.views.WorkHistoryActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class WorkHistory {

    @Rule
    public IntentsTestRule<WorkHistoryActivity> myRule = new IntentsTestRule<>(WorkHistoryActivity.class);

    @Test
    public void titleShows() {
        onView(withText("Work History")).check(matches(isDisplayed()));
    }

    @Test
    public void redirectShows() {
        onView(withText("Landing Page")).check(matches(isDisplayed()));
    }

}

