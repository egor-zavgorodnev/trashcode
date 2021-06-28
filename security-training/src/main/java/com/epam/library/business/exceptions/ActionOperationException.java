package com.epam.library.business.exceptions;

public class ActionOperationException extends Exception {
    private int status;

    public ActionOperationException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
