package ca.dal.cs.csci3130.fastmoney.tests.integrationTests;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.views.LandingPageActivity;
import ca.dal.cs.csci3130.fastmoney.views.LogInActivity;
import ca.dal.cs.csci3130.fastmoney.views.ProfileActivity;
import ca.dal.cs.csci3130.fastmoney.views.RegistrationActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class Profile {
    FirebaseAuth firebaseAuth;

    @Rule
    public IntentsTestRule<LogInActivity> myIntentsTestRule = new IntentsTestRule<>(LogInActivity.class);

    @Before
    public void setUp() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Test
    public void signOutButtonRedirectsToLogInPage() {
        onView(withText("Sign Out")).perform(click());
        intended(hasComponent(LogInActivity.class.getName()));
    }

    @Test
    public void signOutButtonSignsOutUser() {
        onView(withText("Sign Out")).perform(click());
        assertTrue("Sign out button signs out user.", firebaseAuth.getCurrentUser() == null);
    }

    @Test
    public void deleteAccountButtonSignsOutUser() {
        onView(withText("Delete Account")).perform(click());
        assertTrue("Delete account button signs out user.", firebaseAuth.getCurrentUser() == null);
    }

    @Test
    public void deleteAccountButtonRedirectsToRegistrationPage() {
        onView(withText("Delete Account")).perform(click());
        intended(hasComponent(RegistrationActivity.class.getName()));
    }

}