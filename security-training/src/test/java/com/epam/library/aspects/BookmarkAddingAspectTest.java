package com.epam.library.aspects;

import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.exceptions.BookmarkOperationException;
import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.interfaces.IBookmarkService;
import com.epam.library.dao.repositories.*;
import com.epam.library.entities.Author;
import com.epam.library.entities.Book;
import com.epam.library.entities.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.util.UUID;

@DataJpaTest
@ComponentScan("com.epam.library")
public class BookmarkAddingAspectTest {

    @Autowired
	UserRepository userRepository;

    @Autowired
	BookRepository bookRepository;

    @Autowired
	AuthorRepository authorRepository;

    @Autowired
	HistoryRepository historyRepository;

    @Autowired
	ActionRepository actionRepository;

    @Autowired
    IBookmarkService bookmarkService;

    @Test
    public void handleSuccessAddingBookmark() throws BookmarkOperationException, UserOperationException, BookOperationException {
        User user = new User(UUID.randomUUID(), "user", "pass", false, false);
        Author author = new Author(UUID.randomUUID(), "first", "second", "last", LocalDate.now());
        Book book = new Book(UUID.randomUUID(), "name", 2012, "000-0000000000", "publisher_name", 123, author);
        userRepository.save(user);
        authorRepository.save(author);
        bookRepository.save(book);

        bookmarkService.addNewBookmark(user.getId(),book.getId(),123);

        Assert.assertEquals(1, historyRepository.count());
    }

    @Test
    public void handleErrorAddingBookmark() {
        try {
            bookmarkService.addNewBookmark(UUID.randomUUID(),UUID.randomUUID(),123);
        } catch (Exception ignored) {
        }

        Assert.assertEquals(1, actionRepository.count());
    }


}
