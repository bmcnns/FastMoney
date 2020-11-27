package ca.dal.cs.csci3130.fastmoney.models;

public class Job {
    private String title;
    private String employer;
    private String location;
    private double payRate;
    private JobType jobType;

    public Job(String title,
               String employer,
               String location,
               double payRate,
               JobType jobType) {
        this.title = title;
        this.employer = employer;
        this.location = location;
        this.payRate = payRate;
        this.jobType = jobType;
    }

    public String getTitle() {
        return this.title;
    }

    public String getEmployer() {
        return this.employer;
    }

    public String getLocation() {
        return this.location;
    }

    public double getPayRate() {
        return this.payRate;
    }

    public JobType getJobType() {
        return this.jobType;
    }
}
