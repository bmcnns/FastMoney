package ca.dal.cs.csci3130.fastmoney.models;

import java.util.Date;
import java.util.List;

public class Job {
    static final int MINIMUM_PAY_RATE = 1;
    static final int MAXIMUM_PAY_RATE = 999;
    static final int MAXIMUM_DESCRIPTION_LENGTH = 499;
    static final int MAXIMUM_TITLE_LENGTH = 50;
    static final int MAXIMUM_IMAGE_COUNT = 10;

    String title;
    int payRate;
    String description;
    List<String> images;
    Date postedDate;
    User employer;
    User employee;

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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void addImage(String image) { this.images.add(image); }

    public void removeImage(String image) {
        this.images.remove(image);
    }


    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public User getEmployer() {
        return employer;
    }

    public void setEmployer(User employer) {
        this.employer = employer;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public Job(String title,
               int payRate,
               String description,
               List<String> images,
               User employer,
               User employee,
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
               List<String> images,
               User employer,
               User employee) {
        this(title, payRate, description, images, employer, employee, new Date());
    }

    public static boolean isTitleValid(String title) {
        if (title.length() > Job.MAXIMUM_TITLE_LENGTH)
            return false;
        else if (title.length() < 1)
            return false;
        else
            return true;
    }

    public static boolean isPayRateValid(int payRate) {
        if (payRate > Job.MAXIMUM_PAY_RATE)
            return false;
        else if (payRate < Job.MINIMUM_PAY_RATE)
            return false;
        else
            return true;
    }

    public static boolean isDescriptionValid(String description) {
        if (description.length() > Job.MAXIMUM_DESCRIPTION_LENGTH)
            return false;
        else if (description.length() < 1)
            return false;
        else
            return true;
    }

    public static boolean isImagesValid(List<String> images) {
        if (images.size() < 1)
            return false;
        else if (images.size() > Job.MAXIMUM_IMAGE_COUNT)
            return false;
        else
            return true;
    }

    private boolean isEmployerValid() {
        return User.isValid(getEmployer());
    }

    private boolean isEmployeeValid() {
        if (getEmployee() == null)
            return true;
        else
            return User.isValid(getEmployee());
    }

    private boolean isPostedDateValid() {
        if (getPostedDate().after(new Date()))
            return false;
        else
            return true;
    }

    public static boolean isValid(Job job) {
        return (
                isTitleValid(job.getTitle())
                        && isPayRateValid(job.getPayRate())
                        && isDescriptionValid(job.getDescription())
                        && isImagesValid(job.getImages())
                        && job.isEmployerValid()
                        && job.isEmployeeValid()
                        && job.isPostedDateValid()
        );
    }
}
