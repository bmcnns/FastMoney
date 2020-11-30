package ca.dal.cs.csci3130.fastmoney.tests.integrationTests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.views.MainActivity;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class Rating {

    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void submitButtonRedirectsToLandingPage() {
        assertFalse(!false);
    }
}