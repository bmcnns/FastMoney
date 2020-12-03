package ca.dal.cs.csci3130.fastmoney.tests.espressoTests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.views.WorkHistoryActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class WorkHistory {

    @Rule
    ActivityScenarioRule<WorkHistoryActivity> myRule=new ActivityScenarioRule<>(WorkHistoryActivity.class);

    @Test
    public void titleShows(){
       onView(withText("Work History")).check(matches(isDisplayed()));
   }

    @Test
    public void listShows(){onView(withId(R.id.workList)).check(matches(isDisplayed()));}

    @Test
    public void correctJobShows(){assertTrue(false);}

    @Test
    public void incorrectJobDoesNotShow(){assertTrue(false);}
}
