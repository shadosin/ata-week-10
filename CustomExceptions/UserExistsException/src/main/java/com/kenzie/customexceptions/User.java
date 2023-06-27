package com.kenzie.customexceptions;

/**
 * POJO representing a User, containing name, email, and phone number.
 */
public class User {

    private String name;
    private String email;
    private Integer phoneNumber;

    /**
     * Class POJO for User.
     * @param name - user name
     * @param email - user email
     * @param phoneNumber - user phone number
     */
    public User(String name, String email, Integer phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
