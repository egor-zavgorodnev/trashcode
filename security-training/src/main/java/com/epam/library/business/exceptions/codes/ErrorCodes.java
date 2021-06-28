package com.epam.library.business.exceptions.codes;

public enum ErrorCodes {

    //Date Exceptions
    DATE_IS_GREATER_THAN_THE_CURRENT(1),
    //Author Exceptions
    AUTHOR_NOT_FOUND(10),
    FAILED_TO_DELETE_AUTHOR(11),
    FAILED_TO_ADD_AUTHOR(12),
    // Book Exceptions
    BOOK_NOT_FOUND(20),
    INCORRECT_ISBN(21),
    BOOK_WITH_LESS_THAN_ONE_PAGE(22),
    A_BOOK_WITH_SUCH_ISBN_ALREADY_EXISTS(23),
    FAILED_TO_ADD_BOOK(24),
    INCORRECT_RELEASE_YEAR(25),
    FAILED_TO_DELETE_BOOK(26),
    //Bookmark Exceptions
    BOOKMARK_NOT_FOUND(30),
    MORE_PAGES_THAN_IN_THE_BOOK(31),
    PAGES_COUNT_LESS_THEN_ONE(32),
    FAILED_TO_ADD_BOOKMARK(33),
    FAILED_TO_DELETE_BOOKMARK(34),
    // History Exceptions
    HISTORY_NOT_FOUND(40),
    FAILED_TO_DELETE_HISTORY(41),
    FAILED_TO_ADD_HISTORY(42),
    // User Exceptions
    USER_NOT_FOUND(50),
    USER_IS_BLOCKED(51),
    NICKNAME_ALREADY_EXIST(52),
    FAILED_TO_UNLOCK_USER(53),
    FAILED_TO_LOCK_USER(54),
    // Action Exceptions
    ACTION_NOT_FOUND(60);

    private final int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    }
