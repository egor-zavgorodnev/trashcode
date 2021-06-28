package com.epam.library.business.interfaces;


import com.epam.library.entities.Book;
import com.epam.library.business.exceptions.AuthorOperationException;
import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.exceptions.DateException;

import java.util.List;
import java.util.UUID;

public interface IBookService {
    void addNewBook(String bookName, int releaseYear, String isbn, String publisher, int pageCount, UUID authorId) throws BookOperationException, DateException, AuthorOperationException;

    void deleteBook(UUID bookId) throws BookOperationException;

    List<Book> getAllBooks();
}
