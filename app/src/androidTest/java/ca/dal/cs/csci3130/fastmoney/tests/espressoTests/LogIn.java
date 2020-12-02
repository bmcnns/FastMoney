package ca.dal.cs.csci3130.fastmoney.tests.espressoTests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.views.LogInActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LogIn {
    @Rule
    public ActivityScenarioRule<LogInActivity> myRule = new ActivityScenarioRule<>(LogInActivity.class);

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
        onView(withId(R.id.signInButton)).check(matches(isDisplayed()));
    }

    public void showRegistrationButton(){
        onView(withId(R.id.regButton)).check(matches(isDisplayed()));
    }

    //ensure proper error handling for empty sign in attempts
    @Test
    public void emptySignInAttempt(){
        onView(withId(R.id.EmailAddress)).perform(typeText(""));
        onView(withId(R.id.Password)).perform(typeText(""));
        onView(withId(R.id.signInButton)).perform(click());
        onView(withText("Empty email or password")).check(matches(isDisplayed()));
    }

    //ensure proper handling of invalid sign in attempts
    @Test
    public void invalidUserSignIn(){
        onView(withId(R.id.EmailAddress)).perform(typeText("this-is-not-a-valid-email"));
        onView(withId(R.id.Password)).perform(typeText("~!@#$%^&*"));
        onView(withId(R.id.signInButton)).perform(click());
        onView(withText("Invalid email or password")).check(matches(isDisplayed()));
    }

}
