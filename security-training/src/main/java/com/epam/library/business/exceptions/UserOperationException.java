package com.epam.library.business.exceptions;

public class UserOperationException extends Exception {

    private int status;

    public UserOperationException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
