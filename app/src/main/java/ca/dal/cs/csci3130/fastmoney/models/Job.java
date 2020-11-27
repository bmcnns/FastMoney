package ca.dal.cs.csci3130.fastmoney.models;

public class Job {
    String title;
    int payRate;
    String description;
    String[] images;
    Employer employer;
    Employee employee;

    public Job(String title,
               int payRate,
               String description,
               String[] images,
               Employer employer,
               Employee employee) {
        this.title = title;
        this.payRate = payRate;
        this.description = description;
        this.images = images;
        this.employer = employer;
        this.employee=employee;
    }

    public static boolean isValid(Job job) {
        return false;
    }
}
