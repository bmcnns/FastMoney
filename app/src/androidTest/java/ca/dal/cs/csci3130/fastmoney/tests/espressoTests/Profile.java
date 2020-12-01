package ca.dal.cs.csci3130.fastmoney.tests.espressoTests;

import android.content.Context;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
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
public class Profile {

    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("ca.dal.cs.csci3130.fastmoney.examples.espressoTests", appContext.getPackageName());
    }

    @Test
    public void showsRatings() {
        assertFalse(!false);
    }

    @Test
    public void showsEditButtonWhenNotEditing() {
        assertFalse(!false);
    }

    @Test
    public void showsEditButtonFocusedWhenEditing() {
        assertFalse(!false);
    }

    @Test
    public void creditCardIsCensored() {
        assertFalse(!false);
    }

    @Test
    public void showsProfileImage() {
        assertFalse(!false);
    }

    @Test
    public void showsSignOutButton() {
        assertFalse(!false);
    }

    @Test
    public void showsDeleteAccountButton() {
        assertFalse(!false);
    }

    @Test
    public void showsProfileInformation() {
        assertFalse(!false);
    }

    @Test
    public void creditCardsCantBeSetToBlank() {
        assertFalse(!false);
    }

    @Test
    public void emailsCantBeSetToBlank() {
        assertFalse(!false);
    }
}