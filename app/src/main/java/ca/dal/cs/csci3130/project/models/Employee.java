package ca.dal.cs.csci3130.project.models;

public class Employee {
    private String firstName;
    private String lastName;
    private String bio;

    public Employee(String firstName,
                    String lastName,
                    String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getName() {
        return getFirstName() + " " + getLastName();
    }

    public String getBio() {
        return this.bio;
    }
}

