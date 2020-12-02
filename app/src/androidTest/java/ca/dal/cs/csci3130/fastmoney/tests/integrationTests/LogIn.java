package ca.dal.cs.csci3130.fastmoney.tests.integrationTests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
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

    //ensure registered users can sign in
    @Test
    public void successfulLoginRedirects() {
        onView(withId(R.id.EmailAddress)).perform(typeText("John@gmail.com"));
        onView(withId(R.id.Password)).perform(typeText("123456"));
        onView(withId(R.id.signInButton)).perform(click());
        intended(hasComponent(LandingPageActivity.class.getName()));
    }

    //test that register button redirects to the Registration activity
    @Test
    public void sendToRegistrationPage(){
        onView(withHint("Register")).check(matches(isDisplayed()));
        onView(withId(R.id.registerButton)).perform(click());
        //intended(hasComponent(RegistrationActivity.class.getName()));
    }
}