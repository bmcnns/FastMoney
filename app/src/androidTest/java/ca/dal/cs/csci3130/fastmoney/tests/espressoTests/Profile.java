package ca.dal.cs.csci3130.fastmoney.tests.espressoTests;

import android.content.Context;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.views.MainActivity;
import ca.dal.cs.csci3130.fastmoney.views.ProfileActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isFocused;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class Profile {

    @Rule
    public ActivityScenarioRule<ProfileActivity> myRule = new ActivityScenarioRule<>(ProfileActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("ca.dal.cs.csci3130.fastmoney.examples.espressoTests", appContext.getPackageName());
    }

    @Test
    public void showsRatings() {
        onView(withText("Employer Rating")).check(matches(isDisplayed()));
        onView(withText("Employee Rating")).check(matches(isDisplayed()));
        onView(withId(R.id.employeeRatingStars)).check(matches(isDisplayed()));
        onView(withId(R.id.employerRatingStars)).check(matches(isDisplayed()));
    }

    @Test
    public void showsEditButtonWhenNotEditing() {
        onView(withId(R.id.editBtn)).check(matches(isDisplayed()));
    }

    // Or some other visual appearance to show a difference between editing and not-editing.
    @Test
    public void showsEditButtonFocusedWhenEditing() {
        onView(withId(R.id.editBtn)).check(matches(isFocused()));
    }

    @Test
    public void creditCardIsCensored() {
        onView(withSubstring("****")).check(matches(isDisplayed()));
    }

    @Test
    public void showsProfileImage() {
        onView(withId(R.id.userImage)).check(matches(isDisplayed()));
    }

    @Test
    public void showsSignOutButton() {
        onView(withText("Sign Out")).check(matches(isDisplayed()));
    }

    @Test
    public void showsDeleteAccountButton() {
        onView(withText("Delete Account")).check(matches(isDisplayed()));
    }

    @Test
    public void showsEmail() {
        onView(withHint("john.doe@example.com")).check(matches(isDisplayed()));
    }

    @Test
    public void showsCreditCard() {
        onView(withHint("**** **** **** 5678")).check(matches(isDisplayed()));
    }

    @Test
    public void showsFirstName() {
        onView(withText("John")).check(matches(isDisplayed()));
        onView(withText("Doe")).check(matches(isDisplayed()));
    }

    @Test
    public void showsLocation() {
        onView(withText("Halifax, Nova Scotia, Canada")).check(matches(isDisplayed()));
    }


    @Test
    public void creditCardsCantBeSetToBlank() {
        onView(withId(R.id.editBtn)).perform(click());
        onView(withHint("**** **** **** 5678")).perform(replaceText(""));
        onView(withText("Error: Credit cards cannot be blank.")).check(matches(isDisplayed()));
        onView(withHint("**** **** **** 5678")).check(matches(withText("**** **** **** 5678")));
    }

    @Test
    public void emailsCantBeSetToBlank() {
        onView(withId(R.id.editBtn)).perform(click());
        onView(withHint("john.doe@example.com")).perform(replaceText(""));
        onView(withText("Error: Credit cards cannot be blank.")).check(matches(isDisplayed()));
        onView(withHint("**** **** **** 5678")).check(matches(withText("**** **** **** 5678")));
    }
}