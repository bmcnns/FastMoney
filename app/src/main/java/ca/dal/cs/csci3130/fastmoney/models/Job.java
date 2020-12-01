package ca.dal.cs.csci3130.fastmoney.models;

import java.util.Date;

public class Job {
    static final int MINIMUM_PAY_RATE = 1;
    static final int MAXIMUM_PAY_RATE = 999;
    static final int MAXIMUM_DESCRIPTION_LENGTH = 499;
    static final int MAXIMUM_TITLE_LENGTH = 50;
    static final int MAXIMUM_IMAGE_COUNT = 10;

    String title;
    int payRate;
    String description;
    String[] images;
    Date postedDate;
    Employer employer;
    Employee employee;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPayRate() {
        return payRate;
    }

    public void setPayRate(int payRate) {
        this.payRate = payRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

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

    private boolean isTitleValid() {
        if (getTitle().length() > Job.MAXIMUM_TITLE_LENGTH)
            return false;
        else if (getTitle().length() < 1)
            return false;
        else
            return true;
    }

    private boolean isPayRateValid() {
        if (getPayRate() > Job.MAXIMUM_PAY_RATE)
            return false;
        else if (getPayRate() < Job.MINIMUM_PAY_RATE)
            return false;
        else
            return true;
    }

    private boolean isDescriptionValid() {
        if (getDescription().length() > Job.MAXIMUM_DESCRIPTION_LENGTH)
            return false;
        else if (getDescription().length() < 1)
            return false;
        else
            return true;
    }

    private boolean isImagesValid() {
        if (getImages().length < 1)
            return false;
        else if (getImages().length > Job.MAXIMUM_IMAGE_COUNT)
            return false;
        else
            return true;
    }

    private boolean isEmployerValid() {
        return getEmployer().isValid();
    }

    private boolean isEmployeeValid() {
        return getEmployee().isValid();
    }

    private boolean isPostedDateValid() {
        if (getPostedDate().after(new Date()))
            return false;
        else
            return true;
    }

    public static boolean isValid(Job job) {
        return (
                job.isTitleValid()
                        && job.isPayRateValid()
                        && job.isDescriptionValid()
                        && job.isImagesValid()
                        && job.isEmployerValid()
                        && job.isEmployeeValid()
                        && job.isPostedDateValid()
        );
    }
}
