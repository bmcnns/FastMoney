package ca.dal.cs.csci3130.project.backEndTests.registrationTests;

import org.junit.Before;
import org.junit.Test;

import ca.dal.cs.csci3130.project.registration.RegistrationValidator;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RegistrationValidationTest {
    RegistrationValidator validator;
    @Before
    public void initialize() {
        validator = new RegistrationValidator();
    }

    @Test
    /**
     * Rejects usernames with non alpha numeric characters
     */
    public void rejectsNonAlphanumericUsername() {
        assertFalse(validator.isValidUsername("abuun*"));
        assertFalse(validator.isValidUsername("@@@"));
        assertFalse(validator.isValidUsername("øusername"));
    }

    @Test
    /**
     * Rejects usernames with no entered value
     */
    public void rejectsEmptyUsername() {
        assertFalse(validator.isValidUsername(""));
    }

    @Test
    /**
     * Accepts valid usernames
     */
    public void acceptsValidUsername() {
        assertTrue(validator.isValidUsername("foo"));
        assertTrue(validator.isValidUsername("bar123"));
        assertTrue(validator.isValidUsername("123bar"));
    }

    @Test
    /**
     * Rejects emails with invalid formats
     */
    public void rejectsInvalidFormatEmail() {
        assertFalse(validator.isValidEmail("Abc.example.com"));
        assertFalse(validator.isValidEmail("@Abc@exampl@e.com"));
        assertFalse(validator.isValidEmail("a\"b(c)d,e:f;gi[j\\k]l"));
    }

    @Test
    /**
     * Rejects emails with empty values
     */
    public void rejectsEmptyEmail() {
        assertFalse(validator.isValidEmail(""));
    }

    /**
     * Accepts valid emails.
     */
    @Test
    public void acceptsValidEmail() {
        assertTrue(validator.isValidEmail("john.doe@example.com"));
    }

}