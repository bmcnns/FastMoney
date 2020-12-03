package ca.dal.cs.csci3130.fastmoney.tests.integrationTests;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.views.LandingPageActivity;
import ca.dal.cs.csci3130.fastmoney.views.LogInActivity;
import ca.dal.cs.csci3130.fastmoney.views.MainActivity;
import ca.dal.cs.csci3130.fastmoney.views.RegistrationActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class Registration {

    @Rule
    public IntentsTestRule<MainActivity> myIntentsTestRule = new IntentsTestRule<>(MainActivity.class);

    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<>(MainActivity .class);

    @Test
    public void successfulRegisterRedirects() throws InterruptedException {
        onView(withId(R.id.regButton)).perform(click());
        onView(withId(R.id.email_field)).perform(typeText("test1@test.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password_field)).perform(typeText("testtest1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.first_name_field)).perform(typeText("fnameTest"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.last_name_field)).perform(typeText("lnameTest"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.registerRGBtn)).perform(click());
        Thread.sleep(4000);
        intended(hasComponent(LandingPageActivity.class.getName()));
        onView(withId(R.id.profileButton)).perform(click());
        onView(withId(R.id.deleteAccountBtn)).perform(click());
    }

    @Test
    public void unsuccessfulRegisterDoesNotRedirect() throws InterruptedException {
        onView(withId(R.id.regButton)).perform(click());
        onView(withId(R.id.email_field)).perform(typeText("test1@test.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password_field)).perform(typeText("testt"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.first_name_field)).perform(typeText("fnameTest"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.last_name_field)).perform(typeText("lnameTest"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.registerRGBtn)).perform(click());
        Thread.sleep(3000);
        intended(hasComponent(RegistrationActivity.class.getName()));
    }

    @Test
    public void signInLinkRedirects() throws InterruptedException {
        onView(withId(R.id.regButton)).perform(click());
        onView(withId(R.id.loginRGBtn)).perform(click());
        Thread.sleep(2000);
        intended(hasComponent(LogInActivity.class.getName()));
    }
}