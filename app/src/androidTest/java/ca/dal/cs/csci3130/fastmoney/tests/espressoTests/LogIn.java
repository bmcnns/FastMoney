package ca.dal.cs.csci3130.fastmoney.tests.espressoTests;

import android.app.Activity;
import android.content.Context;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.views.LandingPageActivity;
import ca.dal.cs.csci3130.fastmoney.views.LogInActivity;
import ca.dal.cs.csci3130.fastmoney.views.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class LogIn {
    @Rule
    public ActivityScenarioRule<LogInActivity> myRule = new ActivityScenarioRule<>(LogInActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("ca.dal.cs.csci3130.fastmoney.examples.espressoTests", appContext.getPackageName());
    }

    //ensure elements properly showing
    @Test
    public void titleShows(){
        onView(withText("Fast Money")).check(matches(isDisplayed()));
    }

    @Test
    public void emailShows(){
        onView(withHint("Email")).check(matches(isDisplayed()));
    }

    @Test
    public void passwordShows(){
        onView(withHint("Password")).check(matches(isDisplayed()));
    }

    @Test
    public void showSignInButton(){
        onView(withHint("Sign In")).check(matches(isDisplayed()));
    }

    //ensure incorrect login credentials are properly handled
    @Test
    public void unsuccessfulLoginDoesNotRedirect() {
        onView(withId(R.id.EmailAddress)).perform(typeText("Jo@gmail.com"));
        onView(withId(R.id.Password)).perform(typeText("123456"));
        onView(withId(R.id.signInButton)).perform(click());
        onView(withText("Incorrect username or password")).check(matches(isDisplayed()));
        intended(hasComponent(LogInActivity.class.getName()));
    }

    //ensure proper error handling for empty sign in attempts
    @Test
    public void emptySignInAttempt(){
        onView(withId(R.id.EmailAddress)).perform(typeText(""));
        onView(withId(R.id.Password)).perform(typeText(""));
        onView(withId(R.id.signInButton)).perform(click());
        onView(withText("Empty username or password")).check(matches(isDisplayed()));
    }

    //ensure proper handling of invalid sign in attempts
    @Test
    public void invalidUserSignIn(){
        onView(withId(R.id.EmailAddress)).perform(typeText("this-is-not-a-valid-email"));
        onView(withId(R.id.Password)).perform(typeText("~!@#$%^&*"));
        onView(withId(R.id.signInButton)).perform(click());
        onView(withText("Invalid username or password")).check(matches(isDisplayed()));
    }

}
