package com.epam.library.business.exceptions;

public class BookOperationException extends Exception {

    private int status;

    public BookOperationException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
