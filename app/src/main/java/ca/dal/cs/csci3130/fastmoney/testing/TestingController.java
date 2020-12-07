package ca.dal.cs.csci3130.fastmoney.testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ca.dal.cs.csci3130.fastmoney.models.Job;
import ca.dal.cs.csci3130.fastmoney.models.User;

import static ca.dal.cs.csci3130.fastmoney.testing.TestUserType.EMPLOYEE;
import static ca.dal.cs.csci3130.fastmoney.testing.TestUserType.EMPLOYER;
import static ca.dal.cs.csci3130.fastmoney.testing.TestUserType.RANDOM;

public abstract class TestingController {
    private static TestingMode testingMode;

    private static User testRandomUser = new User(
            "John",
            "Doe",
            "john.doe@example.com",
            "Halifax, Nova Scotia, Canada",
            "4724090012345678",
            "test/user/placeholder_1.jpg",
            5.0f,
            5.0f
            );

    private static User testEmployee = new User(
            "John",
            "Callinger",
            "john.callinger@example.com",
            "Halifax, Nova Scotia, Canada",
            "4724090012345678",
            "test/user/placeholder_2.jpg",
            5.0f,
            5.0f
    );

    private static User testEmployer = new User(
            "Jane",
            "Doe",
            "jane.doe@example.com",
            "Halifax, Nova Scotia, Canada",
            "4724090012345678",
            "test/user/placeholder_3.jpg",
            5.0f,
            5.0f
    );

    private static Job testJob = new Job(
            "Leaf picker-upper",
            20,
            "Picks up leaves...",
            Arrays.asList(new String[] {
                    "test/job/placeholder_1.png",
                    "test/job/placeholder_2.jpg"
            }),
            testEmployer,
            testEmployee
    );

    public static TestingMode getTestingMode() {
        return TestingController.testingMode;
    }

    private static Job getTestJob() {
        return TestingController.testJob;
    }

    private static User getTestUser() {
        return testRandomUser;
    }

    private static User getTestUser(TestUserType t) {
        switch (t) {
            case RANDOM:
                return testRandomUser;
            case EMPLOYEE:
                return testEmployee;
            case EMPLOYER:
                return testEmployer;
            default:
                return null;
        }
    }
}
