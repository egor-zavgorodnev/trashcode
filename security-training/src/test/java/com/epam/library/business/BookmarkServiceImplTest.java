package com.epam.library.business;

import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.exceptions.BookmarkOperationException;
import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.interfaces.IBookmarkService;
import com.epam.library.entities.Author;
import com.epam.library.entities.Book;
import com.epam.library.entities.Bookmark;
import com.epam.library.entities.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.UUID;

@DataJpaTest
@ComponentScan("com.epam.library")
public class BookmarkServiceImplTest {

    @Autowired
    private CrudRepository<Bookmark, UUID> bookmarkRepository;

    @Autowired
    private IBookmarkService bookmarkService;

    private Bookmark bookmark;
    private User user;
    private Author author;
    private Book book;

    @BeforeEach
    public void testBookmarkInit() {
        user = new User(UUID.randomUUID(), "user", "pass", false, false);
        author = new Author(UUID.randomUUID(), "first", "second", "last", LocalDate.now());
        book = new Book(UUID.randomUUID(), "name", 2012, "000-0000000000", "publisher_name", 123, author);
        bookmark = new Bookmark(UUID.randomUUID(), user, book, 123);
        bookmarkRepository.save(bookmark);
    }

    @Test
    public void addNewBookmarkTest() throws BookmarkOperationException, UserOperationException, BookOperationException {

        bookmarkService.addNewBookmark(user.getId(), book.getId(), 123);

        Assert.assertTrue(bookmarkRepository.existsById(bookmark.getId()));
    }

    @Test
    public void deleteBookmarkTest() throws BookOperationException, BookmarkOperationException {

        bookmarkService.deleteBookmark(bookmark.getId());

        Assert.assertFalse(bookmarkRepository.existsById(bookmark.getId()));
    }

    @Test
    public void findAllBookmarkTest() {
        Bookmark secondBookmark = new Bookmark(UUID.randomUUID(), user, book, 123);
        Bookmark thirdBookmark = new Bookmark(UUID.randomUUID(), user, book, 123);
        bookmarkRepository.save(bookmark);
        bookmarkRepository.save(secondBookmark);
        bookmarkRepository.save(thirdBookmark);

        int actual = bookmarkService.getAllBookmarks().size();

        Assert.assertEquals(3, actual);
    }
}
