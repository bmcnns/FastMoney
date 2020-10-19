package ca.dal.cs.csci3130.project;

import org.junit.Before;
import org.junit.Test;

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
    public void rejectsNonAlphanumericUsername() {
        assertTrue(validator.isValidUsername("abuun*"));
        assertTrue(validator.isValidUsername("@@@"));
        assertTrue(validator.isValidUsername("øusername"));
    }

    @Test
    public void rejectsEmptyUsername() {
        assertTrue(validator.isValidUsername(""));
    }

    @Test
    public void acceptsValidUsername() {
        assertFalse(validator.isValidUsername("foo"));
        assertFalse(validator.isValidUsername("bar123"));
        assertFalse(validator.isValidUsername("123bar"));
    }

    @Test
    public void rejectsInvalidFormatEmail() {
        assertTrue(validator.isValidEmail("Abc.example.com"));
        assertTrue(validator.isValidEmail("@Abc@exampl@e.com"));
        assertTrue(validator.isValidEmail("a\"b(c)d,e:f;gi[j\\k]l"));
    }

    @Test
    public void rejectsEmptyEmail() {
        assertTrue(validator.isValidEmail(""));
    }

    @Test
    public void acceptsValidEmail() {
        assertFalse(validator.isValidEmail("john.doe@example.com"));
    }

}