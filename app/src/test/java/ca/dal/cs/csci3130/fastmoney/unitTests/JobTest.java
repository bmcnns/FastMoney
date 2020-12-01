package ca.dal.cs.csci3130.fastmoney.unitTests;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import ca.dal.cs.csci3130.fastmoney.models.Job;
import ca.dal.cs.csci3130.fastmoney.models.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JobTest {
    String validTitle;
    int validPayRate;
    String validDescription;
    String[] validImages;
    User validEmployer;
    User validEmployee;

    @Before
    public void setUp() {
        validTitle = "Astronaut";
        validPayRate = 20;
        validDescription = "Performs moon walks, covers up information about aliens.";
        validImages = new String[] { "neil.png" };

        validEmployer = new User(
                "John",
                "Doe",
                "john.doe@gmail.com",
                "Halifax, NS. Canada",
                "4724090012345678",
                "profile.png",
                5.0f,
                5.0f
        );

        validEmployee = new User(
                "Jane",
                "Doe",
                "john.doe@gmail.com",
                "Halifax, NS. Canada",
                "4724090012345678",
                "profile.png",
                5.0f,
                5.0f
        );
    }

    @Test
    public void validJob() {
        Job job = new Job(
                validTitle,
                validPayRate,
                validDescription,
                validImages,
                validEmployer,
                validEmployee);

        assertTrue("Valid job is valid.", Job.isValid(job));
    }

    @Test
    public void jobWithNoTitleIsInvalid() {
        Job job = new Job(
                "",
                validPayRate,
                validDescription,
                validImages,
                validEmployer,
                validEmployee
        );
        assertFalse("Job with no title is invalid.", Job.isValid(job));
    }

    @Test
    public void jobWithTitleMoreThanMaxCharactersIsInvalid() {
        Job job = new Job(
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                validPayRate,
                validDescription,
                validImages,
                validEmployer,
                validEmployee
        );

        assertFalse("Job with too long of title is invalid.", Job.isValid(job));
    }

    @Test
    public void jobWithNoDescriptionIsInvalid() {
        Job job = new Job(
                validTitle,
                validPayRate,
                "",
                validImages,
                validEmployer,
                validEmployee
        );
        assertFalse("Job with no description is invalid.", Job.isValid(job));
    }

    @Test
    public void jobWithDescriptionMoreThanMaxCharactersIsInvalid() {
        String invalidDescription = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        Job job = new Job(
                validTitle,
                validPayRate,
                invalidDescription,
                validImages,
                validEmployer,
                validEmployee
        );

        assertFalse("Job with too long of description is invalid.", Job.isValid(job));
    }

    @Test
    public void jobWithZeroPayRateIsInvalid() {
        Job job = new Job(
                validTitle,
                0,
                validDescription,
                validImages,
                validEmployer,
                validEmployee
        );

        assertFalse("Job that pays nothing is invalid.", Job.isValid(job));
    }

    @Test
    public void jobWithNegativePayRateIsInvalid() {
        Job job = new Job(
                validTitle,
                -20,
                validDescription,
                validImages,
                validEmployer,
                validEmployee
        );

        assertFalse("Job that pays a negative amount is invalid.", Job.isValid(job));
    }

    @Test
    public void jobWithMoreThanMaxPayRateIsInvalid() {
        Job job = new Job(
                validTitle,
                1000,
                validDescription,
                validImages,
                validEmployer,
                validEmployee
        );

        assertFalse("Job that pays a negative amount is invalid.", Job.isValid(job));
    }

    @Test
    public void jobWithPostedDateInFutureIsInvalid() {

        Date current = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date future = calendar.getTime();

        Job job = new Job(
                validTitle,
                validPayRate,
                validDescription,
                validImages,
                validEmployer,
                validEmployee,
                future
        );

        assertFalse("Job with a date in the future is invalid.", Job.isValid(job));
    }

    @Test
    public void jobWithInvalidEmployeeIsInvalid() {
        /*Employee invalidEmployee = new Employee();

        Job job = new Job(
                validTitle,
                validPayRate,
                validDescription,
                validImages,
                validEmployer,
                invalidEmployee
        );

        assertFalse("Job with an invalid employee is invalid.", Job.isValid(job));
         */
        assertTrue(false);
    }

    @Test
    public void jobWithInvalidEmployerIsInvalid() {
        /* Employer invalidEmployer = new Employer();

        Job job = new Job(
                validTitle,
                validPayRate,
                validDescription,
                validImages,
                invalidEmployer,
                validEmployee
        );

        assertFalse("Job with an invalid employer is invalid.", Job.isValid(job));
         */
        assertTrue(false);
    }

    @Test
    public void jobWithNoImagesIsInvalid() {
        Job job = new Job(
                validTitle,
                validPayRate,
                validDescription,
                new String[] {},
                validEmployer,
                validEmployee
        );

        assertFalse("A job with no images is invalid.", Job.isValid(job));
    }

    @Test
    public void jobWithMoreThanMaxImagesIsInvalid() {
        String[] tooManyImages = new String[] {
                "image1.jpg", "image2.jpg", "image3.jpg",
                "image4.jpg", "image5.jpg", "image6.jpg",
                "image7.jpg", "image8.jpg", "image9.jpg",
                "image10.jpg", "image11.jpg" };

        Job job = new Job(
                validTitle,
                validPayRate,
                validDescription,
                new String[] {},
                validEmployer,
                validEmployee
        );

        assertFalse("A job with more than the maximum amount of images is invalid.", Job.isValid(job));
    }
}
