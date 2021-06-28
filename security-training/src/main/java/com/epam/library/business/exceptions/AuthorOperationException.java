package com.epam.library.business.exceptions;

public class AuthorOperationException extends Exception {

    private int status;

    public AuthorOperationException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
