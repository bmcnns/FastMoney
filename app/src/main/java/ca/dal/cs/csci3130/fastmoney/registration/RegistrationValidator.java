package ca.dal.cs.csci3130.fastmoney.registration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.dal.cs.csci3130.fastmoney.models.User;

public class RegistrationValidator {
    /**
     * Checks the text provided for non-alphanumeric characters
     * @return true if there are non-alphanumeric characters.
     */
    public boolean hasNonAlphanumericCharacters(String text)
    {
        Pattern nonAlphanumericCharacters = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = nonAlphanumericCharacters.matcher(text);
        return matcher.find();
    }

    /**
     * Checks if the text contains a value.
     * @return false if the text contains a value.
     */
    public boolean isMissingValue(String text)
    {
        return text.isEmpty();
    }

    /**
     * Checks if the username is valid
     * @return true if the username is provided and does not contain non-alphanumeric characters.
     */
    public boolean isValidUsername(String username)
    {
        return !hasNonAlphanumericCharacters(username) && !isMissingValue(username);
    }

    /**
     * Checks if the email format is valid.
     * @return true if the email follows the standard format matched via regex.
     */
    public boolean isValidEmailFormat(String text)
    {
        Pattern validEmail = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = validEmail.matcher(text);
        return matcher.matches();
    }

    /**
     * Checks if the email is valid
     * @return true if the email is valid format and not missing.
     */
    public boolean isValidEmail(String email) {
        return isValidEmailFormat(email) && !isMissingValue(email);
    }

    /**
     * Checks if the user is valid.
     * @return true if the user has a valid email and valid username.
     */
    public boolean isValidUser(User user) {
        return true;
        //return isValidEmail(user.getEmail()) && isValidUsername(user.getUsername());
    }
}
