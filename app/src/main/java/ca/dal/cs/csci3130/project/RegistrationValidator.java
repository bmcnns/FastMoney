package ca.dal.cs.csci3130.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationValidator {
    public boolean hasNonAlphanumericCharacters(String text)
    {
        Pattern nonAlphanumericCharacters = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = nonAlphanumericCharacters.matcher(text);
        return matcher.find();
    }

    public boolean isMissingValue(String text)
    {
        return text.isEmpty();
    }
    public boolean isValidUsername(String username)
    {
        return !hasNonAlphanumericCharacters(username) && !isMissingValue(username);
    }

    public boolean isValidEmailFormat(String text)
    {
        Pattern validEmail = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = validEmail.matcher(text);
        return matcher.matches();
    }

    public boolean isValidEmail(String email) {
        return isValidEmailFormat(email) && !isMissingValue(email);
    }

    public boolean isValidUser(User user) {
        return isValidEmail(user.getEmail()) && isValidUsername(user.getUsername());
    }
}
