package ca.dal.cs.csci3130.fastmoney.models;

import java.time.Instant;
import java.util.Date;

public class Job {
    String title;
    int payRate;
    String description;
    String[] images;
    Date postedDate;
    Employer employer;
    Employee employee;

    public Job(String title,
               int payRate,
               String description,
               String[] images,
               Employer employer,
               Employee employee,
               Date postedDate) {
        this.title = title;
        this.payRate = payRate;
        this.description = description;
        this.images = images;
        this.employer = employer;
        this.employee = employee;
        this.postedDate = postedDate;
    }

    public Job(String title,
               int payRate,
               String description,
               String[] images,
               Employer employer,
               Employee employee) {
        this(title, payRate, description, images, employer, employee, new Date());
    }

    public static boolean isValid(Job job) {
        return false;
    }
}
