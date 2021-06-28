package com.epam.library.business.exceptions;

public class BookmarkOperationException extends Exception {
    private int status;

    public BookmarkOperationException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
