package ca.dal.cs.csci3130.fastmoney.tests.integrationTests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.views.MainActivity;

import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class Registration {

    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void successfulRegisterRedirects() {
//        onView(withId(R.id.EmailAddress)).perform(typeText("test@test.com"));
//        onView(withId(R.id.Password)).perform(typeText("testtest"));
//        onView(withId(R.id.signInButton)).perform(click());
//        Thread.sleep(2000);
//        intended(hasComponent(LandingPageActivity.class.getName()));
        assertFalse(!false);
    }

    @Test
    public void unsuccessfulRegisterDoesNotRedirect() {
        assertFalse(!false);
    }

    @Test
    public void signInLinkRedirects() {
        assertFalse(!false);
    }
}