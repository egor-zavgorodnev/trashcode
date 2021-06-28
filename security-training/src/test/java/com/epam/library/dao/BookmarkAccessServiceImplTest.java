package com.epam.library.dao;

import com.epam.library.dao.interfaces.IBookmarkAccessService;
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
public class BookmarkAccessServiceImplTest {

    @Autowired
    private CrudRepository<Bookmark, UUID> bookmarkRepository;

    @Autowired
    private IBookmarkAccessService bookmarkAccessService;

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
    }

    @Test
    public void addNewBookmarkTest() {

        bookmarkAccessService.addNewEntity(bookmark);

        Assert.assertTrue(bookmarkRepository.existsById(bookmark.getId()));
    }

    @Test
    public void deleteBookmarkTest() {

        bookmarkAccessService.deleteEntity(bookmark);

        Assert.assertFalse(bookmarkRepository.existsById(bookmark.getId()));
    }

    @Test
    public void findAllBookmarkTest() {
        Bookmark secondBookmark = new Bookmark(UUID.randomUUID(), user, book, 123);
        Bookmark thirdBookmark = new Bookmark(UUID.randomUUID(), user, book, 123);
        bookmarkRepository.save(bookmark);
        bookmarkRepository.save(secondBookmark);
        bookmarkRepository.save(thirdBookmark);

        int actual = bookmarkAccessService.getAllEntities().size();

        Assert.assertEquals(3, actual);
    }
}
