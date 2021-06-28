package com.epam.library.dao.interfaces;

import com.epam.library.entities.Book;

import java.util.List;
import java.util.UUID;

public interface ISearchBookAccessService {
    List<Book> getBooksByPartOfName(String partOfName);

    List<Book> getBooksByPartOfAuthorName(String partOfAuthorName);

    Book getBookByIsbn(String isbn);

    List<Book> getBooksByReleaseYearRange(int startYear, int endYear);

    List<Book> getBooksByUserBookmark(UUID userId);

    List<Book> getBooksBySeveralWays(int year, int pageCount, String name);
}
