package ca.dal.cs.csci3130.fastmoney.models;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String location;
    private String creditCard;
    private String image;
    private float employerRating;
    private float employeeRating;

    public User(String firstName,
                String lastName,
                String email,
                String location,
                String creditCard,
                String image,
                float employerRating,
                float employeeRating)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.location = location;
        this.creditCard = creditCard;
        this.image = image;
        this.employerRating = employerRating;
        this.employeeRating = employeeRating;
    }

    public User(String firstName,
                String lastName,
                String email,
                String creditCard) {
        this(firstName, lastName, email, "", creditCard, "", 0.0f, 0.0f);
    }

    public static boolean isValid(User user) {
        return true;
    }
}
