package ca.dal.cs.csci3130.fastmoney.unitTests;

import org.junit.Test;

import ca.dal.cs.csci3130.fastmoney.models.Employee;
import ca.dal.cs.csci3130.fastmoney.models.Employer;
import ca.dal.cs.csci3130.fastmoney.models.Job;

import static org.junit.Assert.*;

public class AddJob {
    @Test
    public void validJobTest() {
        Employer employer = new Employer() {};
        Employee employee = new Employee() {};

        Job job = new Job(
                "Job Title",
                20,
                "Raking and disposing of leaves...",
                new String[] {"image1.jpg", "image2.jpg", "img3.jpg"},
                employer,
                employee
        );

        assertTrue(Job.isValid(job));
    }
}