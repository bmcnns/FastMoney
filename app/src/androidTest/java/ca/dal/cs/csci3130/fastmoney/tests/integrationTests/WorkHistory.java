package ca.dal.cs.csci3130.fastmoney.tests.integrationTests;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

import ca.dal.cs.csci3130.fastmoney.views.LogInActivity;

import static org.junit.Assert.assertTrue;

public class WorkHistory {
    @Rule
    public IntentsTestRule<LogInActivity> myIntentsTestRule = new IntentsTestRule<>(LogInActivity.class);

    @Test
    public void sendsCorrectToJobPage(){assertTrue(false);}

    @Test
    public void redirectToLandingPage(){assertTrue(false);}
}
