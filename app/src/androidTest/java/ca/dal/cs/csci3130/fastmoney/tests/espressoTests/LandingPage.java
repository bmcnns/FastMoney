package ca.dal.cs.csci3130.fastmoney.tests.espressoTests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.views.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
public class LandingPage {

    FirebaseAuth firebaseAuth;
    @Before
    public void initialize() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void showsYourProfileButton() {
        onView(withText("Your profile")).check(matches(isDisplayed()));
    }

    @Test
    public void showsYourWorkButton() {
        onView(withText("Your work")).check(matches(isDisplayed()));
    }

    @Test
    public void showsYourJobsButton() {
        onView(withText("Your jobs")).check(matches(isDisplayed()));
    }

    @Test
    public void showsSignOutLink() {
        onView(withId(R.id.signOutLink)).check(matches(isDisplayed()));
    }

    @Test
    public void signOutLinkHasCorrectName() {
        onView(withId(R.id.signOutLink)).check(matches(withText(containsString("Not Bryce? Sign out."))));
    }

    @Test
    public void showsWelcomeHeader() {
        onView(withText("Welcome, Bryce")).check(matches(isDisplayed()));
    }

    @Test
    public void welcomeHeaderHasCorrectName() {
        onView(withId(R.id.welcomeHeader)).check(matches(withText(containsString("Welcome, Bryce"))));
    }
}