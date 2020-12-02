package ca.dal.cs.csci3130.fastmoney.tests.integrationTests;

import android.app.Activity;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitor;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.views.LandingPageActivity;
import ca.dal.cs.csci3130.fastmoney.views.LogInActivity;
import ca.dal.cs.csci3130.fastmoney.views.RegistrationActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LogIn {
    @Rule
    public IntentsTestRule<LogInActivity> myIntentsTestRule = new IntentsTestRule<>(LogInActivity.class);


    //ensure registered users can sign in
    @Test
    public void successfulLoginRedirects() throws InterruptedException {
        onView(withId(R.id.EmailAddress)).perform(typeText("test@test.com"));
        onView(withId(R.id.Password)).perform(typeText("testtest"));
        onView(withId(R.id.signInButton)).perform(click());
        Thread.sleep(2000);
        intended(hasComponent(LandingPageActivity.class.getName()));
    }

    //test that register button redirects to the Registration activity
    @Test
    public void sendToRegistrationPage() {
        onView(withId(R.id.regButton)).perform(click());
        intended(hasComponent(RegistrationActivity.class.getName()));
    }

}