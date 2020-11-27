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
public class Registration {

    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("ca.dal.cs.csci3130.fastmoney.examples.espressoTests", appContext.getPackageName());
    }

    @Test
    public void showsFirstNameIsRequiredError() {
        assertFalse(!false);
    }

    @Test
    public void showsLastNameIsRequiredError() {
        assertFalse(!false);
    }

    @Test
    public void showsPasswordIsRequiredError() {
        assertFalse(!false);
    }

    @Test
    public void showsCreditCardIsRequiredError() {
        assertFalse(!false);
    }

    @Test
    public void showsInvalidEmailAndPasswordError() {
        assertFalse(!false);
    }

    @Test
    public void showsAccountAlreadyExistsError() {
        assertFalse(!false);
    }
}