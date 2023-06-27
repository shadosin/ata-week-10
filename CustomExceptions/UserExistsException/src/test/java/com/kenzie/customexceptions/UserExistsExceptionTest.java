package com.kenzie.customexceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserExistsExceptionTest {
    private UserManager userManager;
    private User testUser;
    private String testUserName = "James";
    private String testUserEmail = "james@gmail.com";
    private Integer testUserPhoneNumber = 1234567890;

    private String testUserName2 = "John";
    private String testUserEmail2 = "John@gmail.com";
    private Integer testUserPhoneNumber2 = 1876543210;

    @BeforeEach
    public void setup() throws UserExistsException {
        userManager = new UserManager();
        testUser = new User(testUserName, testUserEmail, testUserPhoneNumber);
        userManager.createUser(testUser);
    }

    @Test
    public void userManager_attemptToAddExistingUser_throwsException() {
        assertThrows(UserExistsException.class, () -> {
            userManager.createUser(testUser);
            userManager.createUser(testUser);
        });
    }

    @Test
    public void userManager_attemptToAddNewUser_addsNewUser() throws UserExistsException {
        User testUser2 = new User(testUserName2, testUserEmail2, testUserPhoneNumber2);
        userManager.createUser(testUser2);
        assertEquals(2, userManager.getUserList().size());
    }

    @Test
    public void userExistsException_createNewExceptionEmptyConstructor_hasExpectedBehavior() {
        try {
            throw new UserExistsException();
        } catch (UserExistsException e) {
            assertEquals(UserExistsException.class, e.getClass(), "Exception method has been implemented");
        }
    }

    @Test
    public void userExistsException_createNewExceptionWithMessage_hasExpectedBehavior() {
        String message = "User already exists!";
        try {
            throw new UserExistsException(message);
        } catch (UserExistsException e) {
            assertEquals(message, e.getMessage(), "Exception method throws exception with correct message");
        }
    }

    @Test
    public void userExistsException_createNewExceptionWithMessageAndThrowable_hasExpectedBehavior() {
        String message = "User already exists!";
        Throwable ex = new IllegalArgumentException();
        try {
            throw new UserExistsException(message, ex);
        } catch (UserExistsException e) {
            assertEquals(message, e.getMessage(), "Exception method throws exception with correct message");
            assertEquals(ex, e.getCause());
        }
    }

    @Test
    public void userExistsException_createNewExceptionWithThrowable_hasExpectedBehavior() {
        Throwable ex = new IllegalArgumentException();
        try {
            throw new UserExistsException(ex);
        } catch (UserExistsException e) {
            assertEquals(ex, e.getCause());
        }
    }

}
