package ca.dal.cs.csci3130.fastmoney.unitTests;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import ca.dal.cs.csci3130.fastmoney.models.Job;
import ca.dal.cs.csci3130.fastmoney.models.User;

import static org.junit.Assert.*;

public class ViewJob {
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
    public void titleTest() {
        Job job = new Job(
                validTitle,
                validPayRate,
                validDescription,
                Arrays.asList(validImages),
                validEmployer,
                validEmployee);
        assertEquals("Astronaut", job.getTitle());
    }

    @Test
    public void payRateTest() {
        Job job = new Job(
                validTitle,
                validPayRate,
                validDescription,
                Arrays.asList(validImages),
                validEmployer,
                validEmployee);
        assertEquals(20, job.getPayRate());
    }

    @Test
    public void postedDateTest() {
        Job job = new Job(
                validTitle,
                validPayRate,
                validDescription,
                Arrays.asList(validImages),
                validEmployer,
                validEmployee);
        Date currentDate = new Date();
        assertEquals(currentDate, job.getPostedDate());
    }

    @Test
    public void employerTest() {
        Job job = new Job(
                validTitle,
                validPayRate,
                validDescription,
                Arrays.asList(validImages),
                validEmployer,
                validEmployee);
        assertEquals(validEmployer, job.getEmployer());
    }

    @Test
    public void employeeTest() {
        Job job = new Job(
                validTitle,
                validPayRate,
                validDescription,
                Arrays.asList(validImages),
                validEmployer,
                validEmployee);
        assertEquals(validEmployee, job.getEmployee());
    }
}