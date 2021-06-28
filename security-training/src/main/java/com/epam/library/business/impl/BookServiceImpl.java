package com.epam.library.business.impl;

import com.epam.library.business.exceptions.DateException;
import com.epam.library.business.exceptions.codes.ErrorCodes;
import com.epam.library.business.interfaces.IBookService;
import com.epam.library.entities.Book;
import com.epam.library.business.exceptions.AuthorOperationException;
import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.dao.interfaces.IBookAccessService;
import com.epam.library.dao.repositories.AuthorRepository;
import com.epam.library.dao.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements IBookService {

    private final static String ISBN_PATTERN = "\\d{3}-\\d{10}";
    private final static String YEAR_PATTERN = "\\d{1,4}";
    private final int CURRENT_YEAR = LocalDate.now().getYear();

    @Autowired
    private IBookAccessService bookAccessService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void addNewBook(String bookName, int releaseYear, String isbn, String publisher, int pageCount, UUID authorId) throws BookOperationException, DateException, AuthorOperationException {
        if (!String.valueOf(releaseYear).matches(YEAR_PATTERN)) {
            throw new BookOperationException(ErrorCodes.INCORRECT_RELEASE_YEAR.getCode(), "Некорректный год издания");
        }
        if (releaseYear > CURRENT_YEAR) {
            throw new DateException(ErrorCodes.DATE_IS_GREATER_THAN_THE_CURRENT.getCode(), "Дата больше текущей");
        }
        if (!isbn.matches(ISBN_PATTERN)) {
            throw new BookOperationException(ErrorCodes.INCORRECT_ISBN.getCode(), "Некорректный ISBN книги");
        }
        if (pageCount < 1) {
            throw new BookOperationException(ErrorCodes.BOOK_WITH_LESS_THAN_ONE_PAGE.getCode(), "Не бывает книги с меньше чем одной страницей");
        }
        if (bookAccessService.getAllEntities().stream().anyMatch(b -> b.getISBN().equals(isbn))) {
            throw new BookOperationException(ErrorCodes.A_BOOK_WITH_SUCH_ISBN_ALREADY_EXISTS.getCode(), "Книга с таким ISBN уже существует");
        }

        Book book = new Book(UUID.randomUUID(), bookName, releaseYear, isbn, publisher, pageCount, authorRepository.findById(authorId).get());
        bookAccessService.addNewEntity(book);

        if (!bookRepository.existsById(book.getId())) {
            throw new BookOperationException(ErrorCodes.FAILED_TO_ADD_BOOK.getCode(), "Не удалось добавить книгу");
        }

    }

    @Override
    public void deleteBook(UUID bookId) throws BookOperationException {
        Optional<Book> bookToBeDeleted = bookRepository.findById(bookId);

        bookToBeDeleted.ifPresent(b -> bookAccessService.deleteEntity(b));
        bookToBeDeleted.orElseThrow(() -> new BookOperationException(ErrorCodes.BOOK_NOT_FOUND.getCode(), "Книги с таким ID не существует"));

        if (bookRepository.existsById(bookId)) {
            throw new BookOperationException(ErrorCodes.FAILED_TO_DELETE_BOOK.getCode(), "Не удалось удалить книгу");
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return bookAccessService.getAllEntities();
    }
}
