package ca.dal.cs.csci3130.fastmoney.tests.espressoTests;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.testing.TestingController;
import ca.dal.cs.csci3130.fastmoney.testing.TestingMode;
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
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class Profile {

    @Rule
    public IntentsTestRule<ProfileActivity> myRule = new IntentsTestRule<>(ProfileActivity.class);

    @Before
    public void setUp() {
      TestingController.setTestingMode(TestingMode.ENABLED);
    }

    @After
    public void tearDown() {
        TestingController.setTestingMode(TestingMode.DISABLED);
    }

    @Test
    public void showsRatings() {
        onView(withText("Employer Rating")).check(matches(isDisplayed()));
        onView(withText("Employee Rating")).check(matches(isDisplayed()));
        onView(withId(R.id.profile_employerRating)).check(matches(isDisplayed()));
        onView(withId(R.id.profile_employeeRating)).check(matches(isDisplayed()));
    }

    @Test
    public void showsEditButtonWhenNotEditing() {
        onView(withId(R.id.profile_editButton)).check(matches(isDisplayed()));
    }

    // Or some other visual appearance to show a difference between editing and not-editing.
    @Test
    public void showsEditButtonFocusedWhenEditing() {
        onView(withId(R.id.profile_editButton)).check(matches(isFocused()));
    }

    @Test
    public void creditCardIsCensored() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withSubstring("****")).check(matches(isDisplayed()));
    }

    @Test
    public void showsProfileImage() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.userImage)).check(matches(isDisplayed()));
    }

    @Test
    public void showsSignOutButton() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
    public void emailsCantBeSetToBlank() {
        onView(withId(R.id.profile_editButton)).perform(click());
        onView(withHint("john.doe@example.com")).perform(replaceText(""));
        onView(withText("Error: Email cannot be blank.")).check(matches(isDisplayed()));
        onView(withHint("**** **** **** 5678")).check(matches(withText("**** **** **** 5678")));
    }
}