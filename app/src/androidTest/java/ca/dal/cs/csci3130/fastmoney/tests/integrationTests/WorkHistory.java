package ca.dal.cs.csci3130.fastmoney.tests.integrationTests;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.views.LandingPageActivity;
import ca.dal.cs.csci3130.fastmoney.views.LogInActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

public class WorkHistory{
    @Rule
    public IntentsTestRule<LogInActivity> myIntentsTestRule = new IntentsTestRule<>(LogInActivity.class);

    @Test
    public void sendsCorrectToJobPage(){assertTrue(false);}

    @Test
    public void sendToRegistrationPage() throws InterruptedException {
        onView(withId(R.id.landingPageRedirect)).perform(click());
        Thread.sleep(2000);
        intended(hasComponent(LandingPageActivity.class.getName()));
    }
}
