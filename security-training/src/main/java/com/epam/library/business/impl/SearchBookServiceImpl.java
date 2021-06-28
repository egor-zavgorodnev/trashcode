package com.epam.library.business.impl;

import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.exceptions.codes.ErrorCodes;
import com.epam.library.business.interfaces.ISearchBookService;
import com.epam.library.entities.Book;
import com.epam.library.dao.interfaces.ISearchBookAccessService;
import com.epam.library.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class SearchBookServiceImpl implements ISearchBookService {

    private final String ISBN_PATTERN = "\\d{3}-\\d{10}";
    private final String YEAR_PATTERN = "\\d{1,4}";
    private final int CURRENT_YEAR = LocalDate.now().getYear();

    @Autowired
    ISearchBookAccessService searchBookAccessService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Book> searchBooksByPartOfName(String partOfName) {
        return searchBookAccessService.getBooksByPartOfName(partOfName);
    }

    @Override
    public List<Book> searchBooksByPartOfAuthorName(String partOfAuthorName) {
        return searchBookAccessService.getBooksByPartOfAuthorName(partOfAuthorName);
    }

    @Override
    public Book searchBookByIsbn(String isbn) throws BookOperationException {
        if (!isbn.matches(ISBN_PATTERN)) {
            throw new BookOperationException(ErrorCodes.INCORRECT_ISBN.getCode(), "Некорректный ISBN книги");
        }
        return searchBookAccessService.getBookByIsbn(isbn);
    }

    @Override
    public List<Book> searchBooksByReleaseYearRange(int startYear, int endYear) throws BookOperationException {
        if (!String.valueOf(startYear).matches(YEAR_PATTERN) || !String.valueOf(endYear).matches(YEAR_PATTERN)) {
            throw new BookOperationException(ErrorCodes.INCORRECT_RELEASE_YEAR.getCode(), "Некорректный год издания");
        }
        if (startYear > CURRENT_YEAR || endYear > CURRENT_YEAR) {
            throw new BookOperationException(ErrorCodes.INCORRECT_RELEASE_YEAR.getCode(), "Некорректный год издания");
        }
        return searchBookAccessService.getBooksByReleaseYearRange(startYear, endYear);
    }

    @Override
    public List<Book> searchBooksByUserBookmark(UUID userId) throws UserOperationException {
        if (!userRepository.existsById(userId)) {
            throw new UserOperationException(ErrorCodes.USER_NOT_FOUND.getCode(), "Пользователя с таким ID не существует");
        }
        return searchBookAccessService.getBooksByUserBookmark(userId);
    }

    @Override
    public List<Book> searchBooksBySeveralWays(int year, int pageCount, String partOfName) throws BookOperationException {
        if (!String.valueOf(year).matches(YEAR_PATTERN)) {
            throw new BookOperationException(ErrorCodes.INCORRECT_RELEASE_YEAR.getCode(), "Некорректный год издания");
        }
        if (pageCount < 1) {
            throw new BookOperationException(ErrorCodes.BOOK_WITH_LESS_THAN_ONE_PAGE.getCode(), "Не бывает книги с меньше чем одной страницей");
        }
        return searchBookAccessService.getBooksBySeveralWays(year,pageCount,partOfName);
    }
}
