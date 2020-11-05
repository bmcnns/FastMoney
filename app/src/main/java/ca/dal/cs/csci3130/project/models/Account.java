package ca.dal.cs.csci3130.project.models;

public class Account {

    private String email;
    private String username;

    public Account(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
