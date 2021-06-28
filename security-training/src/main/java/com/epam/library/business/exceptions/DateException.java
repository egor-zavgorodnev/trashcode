package com.epam.library.business.exceptions;

public class DateException extends Exception {
    private int status;

    public DateException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
