package com.epam.library.business.interfaces;

import com.epam.library.entities.Book;
import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.exceptions.UserOperationException;

import java.util.List;
import java.util.UUID;

public interface ISearchBookService {

    List<Book> searchBooksByPartOfName(String partOfName);

    List<Book> searchBooksByPartOfAuthorName(String partOfAuthorName);

    Book searchBookByIsbn(String isbn) throws BookOperationException;

    List<Book> searchBooksByReleaseYearRange(int startYear, int endYear) throws BookOperationException;

    List<Book> searchBooksByUserBookmark(UUID userId) throws UserOperationException;

    List<Book> searchBooksBySeveralWays(int year, int pageCount, String partOfName) throws BookOperationException;
}
