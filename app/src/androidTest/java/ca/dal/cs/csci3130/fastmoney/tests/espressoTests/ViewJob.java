package ca.dal.cs.csci3130.fastmoney.tests.espressoTests;

import android.content.Context;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
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
public class ViewJob {

    @Rule
    public IntentsTestRule<MainActivity> myRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void showsJobDescription() {
        assertFalse(!false);
    }

    @Test
    public void showsImage() {
        assertFalse(!false);
    }

    @Test
    public void showsApplyButtonIfGeneralUser() {
        assertFalse(!false);
    }

    @Test
    public void showsContactButtonIfGeneralUser() {
        assertFalse(!false);
    }

    @Test
    public void showsPostedByIfGeneralUser() {
        assertFalse(!false);
    }

    @Test
    public void showsMessageButtonIfEmployer() {
        assertFalse(!false);
    }

    @Test
    public void showsCompleteButtonIfEmployer() {
        assertFalse(!false);
    }

    @Test
    public void showsFireButtonIfEmployer() {
        assertFalse(!false);
    }

    @Test
    public void showsCompletedByIfEmployer() {
        assertFalse(!false);
    }

    @Test
    public void showsMessageButtonIfEmployee() {
        assertFalse(!false);
    }

    @Test
    public void showsQuitButtonIfEmployee() {
        assertFalse(!false);
    }

    @Test
    public void showsPostedByButtonIfEmployee() {
        assertFalse(!false);
    }
}

