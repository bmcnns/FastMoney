package ca.dal.cs.csci3130.fastmoney.unitTests;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import ca.dal.cs.csci3130.fastmoney.models.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {
    String validFirstName;
    String validLastName;
    String validEmail;
    String validLocation;
    String validCreditCard;
    String validImage;
    float validEmployerRating;
    float validEmployeeRating;

    @Before
    public void setUp() {
        validFirstName = "John";
        validLastName = "Doe";
        validEmail = "john.doe@gmail.com";
        validLocation = "Halifax, NS. Canada";
        validCreditCard = "4724090012345678";
        validImage = "profile.png";
        validEmployerRating = 5.0f;
        validEmployeeRating = 5.0f;
    }

    @Test
    public void validUser() {
        User user = new User(
                validFirstName,
                validLastName,
                validEmail,
                validLocation,
                validCreditCard,
                validImage,
                validEmployerRating,
                validEmployeeRating
        );

        assertTrue("Valid user is valid.", User.isValid(user));
    }

    @Test
    public void userWithFirstNameExceedingMaxCharactersIsInvalid() {
        User user = new User(
                "aaaaaaaaaaaaaaaaaaaaaaaaaa",
                validLastName,
                validEmail,
                validLocation,
                validCreditCard,
                validImage,
                validEmployerRating,
                validEmployeeRating
        );

        assertFalse("User with first name exceeding maximum name length is invalid.", User.isValid(user));
    }

    @Test
    public void userWithFirstNameMissingIsInvalid() {
        User user = new User(
                "",
                validLastName,
                validEmail,
                validLocation,
                validCreditCard,
                validImage,
                validEmployerRating,
                validEmployeeRating
        );

        assertFalse("User with first name missing is invalid.", User.isValid(user));
    }

    @Test
    public void userWithLastNameExceedingMaxCharactersIsInvalid() {
        User user = new User(
                validFirstName,
                "aaaaaaaaaaaaaaaaaaaaaaaaaa",
                validEmail,
                validLocation,
                validCreditCard,
                validImage,
                validEmployerRating,
                validEmployeeRating
        );

        assertFalse("User with last name exceeding maximum name length is invalid.", User.isValid(user));
    }

    @Test
    public void userWithLastNameMissingIsInvalid() {
        User user = new User(
                validFirstName,
                "",
                validEmail,
                validLocation,
                validCreditCard,
                validImage,
                validEmployerRating,
                validEmployeeRating
        );

        assertFalse("User with last name missing is invalid.", User.isValid(user));
    }

    @Test
    public void userWithCreditCardExceedingMaxCharactersIsInvalid() {
        User user = new User(
                validFirstName,
                validLastName,
                validEmail,
                validLocation,
                "11111111111111111",
                validImage,
                validEmployerRating,
                validEmployeeRating
        );

        assertFalse("User with credit card exceeding maximum length is invalid.", User.isValid(user));
    }

    @Test
    public void userWithNoCreditCardIsInvalid() {
        User user = new User(
                validFirstName,
                validLastName,
                validEmail,
                validLocation,
                "",
                validImage,
                validEmployerRating,
                validEmployeeRating
        );

        assertFalse("User with missing credit card information is invalid.", User.isValid(user));
    }

    @Test
    public void userWithInvalidCreditCardFormatIsInvalid() {
        User user = new User(
                validFirstName,
                validLastName,
                validEmail,
                validLocation,
                "11111111111111a1",
                validImage,
                validEmployerRating,
                validEmployeeRating
        );

        assertFalse("User with invalid credit card information is invalid.", User.isValid(user));
    }

    @Test
    public void userWithMissingEmailIsInvalid() {
        User user = new User(
                validFirstName,
                validLastName,
                "",
                validLocation,
                validCreditCard,
                validImage,
                validEmployerRating,
                validEmployeeRating
        );

        assertFalse("User with email missing is invalid.", User.isValid(user));
    }

    @Test
    public void userWithEmailExceedingMaxCharacterLengthIsInvalid() {
        User user = new User(
                validFirstName,
                validLastName,
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@gmail.com",
                validLocation,
                validCreditCard,
                validImage,
                validEmployerRating,
                validEmployeeRating
        );

        assertFalse("User with email exceeding maximum length is invalid.", User.isValid(user));
    }

    @Test
    public void userWithImproperEmailFormatIsInvalid() {
        User user = new User(
                validFirstName,
                validLastName,
                "john.doegmail.com",
                validLocation,
                validCreditCard,
                validImage,
                validEmployerRating,
                validEmployeeRating
        );

        assertFalse("User with invalid email is invalid.", User.isValid(user));
    }

    @Test
    public void userWithEmployerRatingBelowMinimumValueIsInvalid() {
        User user = new User(
                validFirstName,
                validLastName,
                validEmail,
                validLocation,
                validCreditCard,
                validImage,
                -0.01f,
                validEmployeeRating
        );

        assertFalse("User with employer rating below minimum value is invalid.", User.isValid(user));
    }

    @Test
    public void userWithEmployerRatingAboveMaximumValueIsInvalid() {
        User user = new User(
                validFirstName,
                validLastName,
                validEmail,
                validLocation,
                validCreditCard,
                validImage,
                5.01f,
                validEmployeeRating
        );

        assertFalse("User with employer rating above maximum value is invalid.", User.isValid(user));
    }

    @Test
    public void userWithEmployeeRatingBelowMinimumValueIsInvalid() {
        User user = new User(
                validFirstName,
                validLastName,
                validEmail,
                validLocation,
                validCreditCard,
                validImage,
                validEmployerRating,
                -0.01f
        );

        assertFalse("User with employee rating below minimum value is invalid.", User.isValid(user));
    }

    @Test
    public void userWithEmployeeRatingAboveMaximumValueIsInvalid() {
        User user = new User(
                validFirstName,
                validLastName,
                validEmail,
                validLocation,
                validCreditCard,
                validImage,
                validEmployerRating,
                5.01f
        );

        assertFalse("User with employer rating above maximum value is invalid.", User.isValid(user));
    }

    @Test
    public void userWithNoImageIsInvalid() {
        User user = new User(
                validFirstName,
                validLastName,
                validEmail,
                validLocation,
                validCreditCard,
                "",
                validEmployerRating,
                validEmployeeRating
        );

        assertFalse("User with no image is invalid.", User.isValid(user));
    }
}

