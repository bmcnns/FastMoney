package ca.dal.cs.csci3130.fastmoney.tests.integrationTests;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.views.MainActivity;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class Hire {

    @Rule
    public IntentsTestRule<MainActivity> myRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void jobCardsRedirectToJob() {
        assertFalse(!false);
    }
}