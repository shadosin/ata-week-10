package com.kenzie.icecreamparlor.exception;

/**
 * Exception for when IceCreamParlorService receives a request for a flavor that does not exist.
 */
public class NoSuchFlavorException extends Exception {

    private static final long serialVersionUID = 8300020541283567118L;

    public NoSuchFlavorException() {
        super();
    }

    public NoSuchFlavorException(String message) {
        super(message);
    }

    public NoSuchFlavorException(Throwable cause) {
        super(cause);
    }

    public NoSuchFlavorException(String message, Throwable cause) {
        super(message, cause);
    }
}
