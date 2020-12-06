package ca.dal.cs.csci3130.fastmoney.models;

public class User {
    private final static float MAXIMUM_RATING_VALUE = 5.0f;
    private final static float MINIMUM_RATING_VALUE = 0.0f;
    private final static int MAXIMUM_NAME_LENGTH = 25;
    private final static int MAXIMUM_EMAIL_LENGTH = 100;
    private final static int MAXIMUM_CREDIT_CARD_LENGTH = 16;

    private String firstName;
    private String lastName;
    private String email;
    private String location;
    private String creditCard;
    private String image;
    private float employerRating;
    private float employeeRating;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getEmployerRating() {
        return employerRating;
    }

    public void setEmployerRating(float employerRating) {
        this.employerRating = employerRating;
    }

    public float getEmployeeRating() {
        return employeeRating;
    }

    public void setEmployeeRating(float employeeRating) {
        this.employeeRating = employeeRating;
    }

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

    private boolean isValidName(String name) {
        if (name.length() > User.MAXIMUM_NAME_LENGTH)
            return false;
        else if (name.length() < 1)
            return false;
        else
            return true;
    }

    private boolean hasValidEmail() {
        if (getEmail().length() > User.MAXIMUM_EMAIL_LENGTH)
            return false;
        else if (getEmail().length() < 1)
            return false;
        else if (!getEmail().contains("@"))
            return false;
        else if (!getEmail().contains("."))
            return false;
        else
            return true;
    }

    private boolean hasValidLocation() {
        return true;
    }

    private boolean hasValidCreditCard() {
        if (getCreditCard().length() > MAXIMUM_CREDIT_CARD_LENGTH)
            return false;
        else if (getCreditCard().length() < 1)
            return false;
        else if (getCreditCard().matches("[a-zA-Z]"))
            return false;
        else
            return true;
    }

    private boolean hasValidImage() {
        if (getImage().length() < 1)
            return false;
        else
            return true;
    }

    private boolean isValidRating(float rating) {
        if (rating > MAXIMUM_RATING_VALUE)
            return false;
        else if (rating < MINIMUM_RATING_VALUE)
            return false;
        else
            return true;
    }

    public static boolean isValid(User user) {
        return (
                user.isValidName(user.getFirstName())
                        && user.isValidName(user.getLastName())
                        && user.hasValidEmail()
                        && user.hasValidLocation()
                        && user.hasValidCreditCard()
                        && user.hasValidImage()
                        && user.isValidRating(user.getEmployerRating())
                        && user.isValidRating(user.getEmployeeRating())
        );
    }
}
