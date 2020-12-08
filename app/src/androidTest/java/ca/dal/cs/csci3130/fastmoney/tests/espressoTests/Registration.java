package ca.dal.cs.csci3130.fastmoney.tests.espressoTests;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.views.MainActivity;
import ca.dal.cs.csci3130.fastmoney.views.RegistrationActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class Registration {

    @Rule
    public IntentsTestRule<MainActivity> myIntentsTestRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("ca.dal.cs.csci3130.fastmoney.views", appContext.getPackageName());
    }

    @Test
    public void showsFirstNameIsRequiredError() throws InterruptedException {
        onView(withId(R.id.regButton)).perform(click());
        onView(withId(R.id.email_field)).perform(typeText("test1@test.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password_field)).perform(typeText("testtest1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.last_name_field)).perform(typeText("lnameTest"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.registerRGBtn)).perform(click());
        Thread.sleep(4000);
        intended(hasComponent(RegistrationActivity.class.getName()));
    }

    @Test
    public void showsLastNameIsRequiredError() throws InterruptedException {
        onView(withId(R.id.regButton)).perform(click());
        onView(withId(R.id.email_field)).perform(typeText("test1@test.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password_field)).perform(typeText("testtest1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.first_name_field)).perform(typeText("fnameTest"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.registerRGBtn)).perform(click());
        Thread.sleep(4000);
        intended(hasComponent(RegistrationActivity.class.getName()));
    }

    @Test
    public void showsPasswordIsRequiredError() throws InterruptedException {
        onView(withId(R.id.regButton)).perform(click());
        onView(withId(R.id.email_field)).perform(typeText("test1@test.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.first_name_field)).perform(typeText("fnameTest"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.last_name_field)).perform(typeText("lnameTest"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.registerRGBtn)).perform(click());
        Thread.sleep(5000);
        intended(hasComponent(RegistrationActivity.class.getName()));
    }

//    @Test
//    public void showsCreditCardIsRequiredError() {
//        assertFalse(!false);
//    }

    @Test
    public void showsInvalidEmailAndPasswordError() throws InterruptedException {
        onView(withId(R.id.regButton)).perform(click());
        onView(withId(R.id.email_field)).perform(typeText("test1test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password_field)).perform(typeText("testt1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.first_name_field)).perform(typeText("fnameTest"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.last_name_field)).perform(typeText("lnameTest"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.registerRGBtn)).perform(click());
        Thread.sleep(5000);
        intended(hasComponent(RegistrationActivity.class.getName()));
    }

    @Test
    public void showsAccountAlreadyExistsError() throws InterruptedException {
        onView(withId(R.id.regButton)).perform(click());
        onView(withId(R.id.email_field)).perform(typeText("test@test.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password_field)).perform(typeText("test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.first_name_field)).perform(typeText("fnameTest"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.last_name_field)).perform(typeText("lnameTest"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.registerRGBtn)).perform(click());
        Thread.sleep(5000);
        intended(hasComponent(RegistrationActivity.class.getName()));
    }
}