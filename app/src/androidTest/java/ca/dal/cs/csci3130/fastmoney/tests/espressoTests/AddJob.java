package ca.dal.cs.csci3130.fastmoney.tests.espressoTests;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.views.AddJobActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AddJob {

    @Rule
    public IntentsTestRule<AddJobActivity> myRule = new IntentsTestRule<>(AddJobActivity.class);

    @Test
    public void showsJobTitleInput() {
        onView(withText("Job Title")).check(matches(isDisplayed()));
        onView(withHint("John")).check(matches(isDisplayed()));
    }

    @Test
    public void showsPayRateInput() {
        onView(withText("Pay Rate")).check(matches(isDisplayed()));
        onView(withHint("$0/hr")).check(matches(isDisplayed()));
    }

    @Test
    public void showsJobDescriptionInput() {
        onView(withText("Job Description")).check(matches(isDisplayed()));
        onView(withHint("Raking and disposing of leaves...")).check(matches(isDisplayed()));
    }

    @Test
    public void showsImagesInput() {
        onView(withText("Images")).check(matches(isDisplayed()));
    }

    @Test
    public void showsPostButton() {
        onView(withText("Post")).perform(scrollTo());
        onView(withText("Post")).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenTitleIsNotAdded() {
        onView(withText("Post")).perform(scrollTo(), click());
        onView(withText("Error: you must enter a valid job title.")).perform(scrollTo());
        onView(withText("Error: you must enter a valid job title.")).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenTitleIsOverMaxLength() {
        String absurdlyLargeString = "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww";
        onView(withHint("John")).perform(scrollTo(), replaceText(absurdlyLargeString));
        onView(withText("Post")).perform(scrollTo(), click());
        onView(withText("Error: you must enter a valid job title.")).perform(scrollTo());
        onView(withText("Error: you must enter a valid job title.")).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenPayRateIsNotAdded() {
        onView(withHint("$0/hr")).perform(clearText());
        onView(withText("Post")).perform(scrollTo(),click());
        onView(withText("Error: you must enter a valid pay rate.")).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenPayRateIsLowerThanMinimumValue() {
        String minimumPayRate = "$1/hr";
        onView(withHint("$0/hr")).perform(clearText());
        onView(withText("Post")).perform(scrollTo(),click());
        onView(withText("Error: you must enter a valid pay rate.")).perform(scrollTo());
        onView(withText("Error: you must enter a valid pay rate.")).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenPayRateIsHigherThanMaximumValue() {
        String maximumPayRate = "1000";
        onView(withHint("$0/hr")).perform(clearText());
        onView(withHint("$0/hr")).perform(typeText(maximumPayRate));
        onView(withText("Pay Rate")).perform(scrollTo());
        onView(withText("Images")).perform(scrollTo());
        onView(withText("Post")).perform(scrollTo(),click());
        onView(withText("Error: you must enter a valid pay rate.")).perform(scrollTo());
        onView(withText("Error: you must enter a valid pay rate.")).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenDescriptionIsNotAdded() {
        onView(withHint("Raking and disposing of leaves...")).perform(clearText());
        onView(withText("Post")).perform(scrollTo(),click());
        onView(withText("Error: you must add a valid description.")).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenDescriptionIsNotValidLength() {
        String absurdlyLargeString = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        onView(withHint("Raking and disposing of leaves...")).perform(scrollTo(),clearText());
        onView(withHint("Raking and disposing of leaves...")).perform(replaceText(absurdlyLargeString));
        onView(withText("Post")).perform(scrollTo(), click());
        onView(withText("Error: you must add a valid description.")).perform(scrollTo());
        onView(withText("Error: you must add a valid description.")).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenMinimumAmountOfImagesNotIncluded() {
        onView(withText("Post")).perform(scrollTo(), click());
        onView(withText("Error: you must add at least one image.")).check(matches(isDisplayed()));
    }

}