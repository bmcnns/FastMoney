package ca.dal.cs.csci3130.fastmoney.unitTests;

import org.junit.Test;

import ca.dal.cs.csci3130.fastmoney.models.User;

import static org.junit.Assert.*;

public class LogIn {
    @Test
    public void emailTest() {
        User user = new User(
                "John",
                "Doe",
                "john.doe@gmail.com",
                "Halifax, NS. Canada",
                "4724090012345678",
                "profile.png",
                5.0f,
                5.0f
        );
        assertEquals("john.doe@gmail.com", user.getEmail());
    }

    @Test
    public void passwordTest() {
        assertFalse(!false);
    }
}