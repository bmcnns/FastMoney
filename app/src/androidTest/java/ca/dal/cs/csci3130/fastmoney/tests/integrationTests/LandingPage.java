package ca.dal.cs.csci3130.fastmoney.tests.integrationTests;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.views.AddJobActivity;
import ca.dal.cs.csci3130.fastmoney.views.FindJobActivity;
import ca.dal.cs.csci3130.fastmoney.views.JobActivity;
import ca.dal.cs.csci3130.fastmoney.views.LandingPageActivity;
import ca.dal.cs.csci3130.fastmoney.views.LogInActivity;
import ca.dal.cs.csci3130.fastmoney.views.ProfileActivity;
import ca.dal.cs.csci3130.fastmoney.views.WorkActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LandingPage {
    @Rule
    public IntentsTestRule<LandingPageActivity> intentsTestRule =
            new IntentsTestRule<>(LandingPageActivity.class);

    @Test
    public void yourProfileButtonRedirectsToProfilePage() {
        onView(withId(R.id.profileButton)).perform(click());
        intended(hasComponent(ProfileActivity.class.getName()));
    }

    @Test
    public void yourWorkButtonRedirectsToWorkPage() {
        onView(withId(R.id.workButton)).perform(click());
        intended(hasComponent(WorkActivity.class.getName()));
    }

    @Test
    public void yourJobsButtonRedirectsToJobPage() {
        onView(withId(R.id.jobsButton)).perform(click());
        intended(hasComponent(JobActivity.class.getName()));
    }

    @Test
    public void postJobButtonRedirectsToPostJobPage() {
        onView(withId(R.id.postJobButton)).perform(click());
        intended(hasComponent(AddJobActivity.class.getName()));
    }

    @Test
    public void findJobButtonRedirectsToFindJobPage() {
        onView(withId(R.id.findJobButton)).perform(click());
        intended(hasComponent(FindJobActivity.class.getName()));
    }

    @Test
    public void signOutLinkRedirectsToLogInPage() {
        onView(withId(R.id.signOutLink)).perform(click());
        intended(hasComponent(LogInActivity.class.getName()));
    }
}