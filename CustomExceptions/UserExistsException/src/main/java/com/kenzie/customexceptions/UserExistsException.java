package com.kenzie.customexceptions;

/**
 * Custom exception for when we try to create a new user but one with
 * duplicate fields already exists.
 */
public class UserExistsException extends Exception {

    public UserExistsException() {
        //implement this method
    }

    public UserExistsException(String message) {
        //implement this method
    }

    public UserExistsException(String message, Throwable e) {
        //implement this method
    }

    public UserExistsException(Throwable e) {
        //implement this method
    }
}
