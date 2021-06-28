package com.epam.library.business.exceptions;

public class HistoryOperationException extends Exception {
    private int status;

    public HistoryOperationException (int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
