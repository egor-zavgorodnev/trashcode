package com.epam.library.dao;

import com.epam.library.dao.interfaces.ISearchBookAccessService;
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
public class SearchBookAccessServiceImplImplTest {

    @Autowired
    private CrudRepository<Book, UUID> bookRepository;
    @Autowired
    private CrudRepository<User, UUID> userRepository;
    @Autowired
    private CrudRepository<Bookmark, UUID> bookmarkRepository;
    @Autowired
    private ISearchBookAccessService searchBookAccessService;
    @Autowired
    private CrudRepository<Author, UUID> authorCrudRepository;

    private Author author;
    private Book book;
    private Book secondBook;
    private Book thirdBook;

    @BeforeEach
    public void testBookInit() {
        author = new Author(UUID.randomUUID(), "first", "second", "last", LocalDate.now());
        authorCrudRepository.save(author);

        book = new Book(UUID.randomUUID(), "name", 2012, "000-0000000000", "publisher_name", 123, author);
        secondBook = new Book(UUID.randomUUID(), "name", 2012, "000-0000000001", "publisher_name", 123, author);
        thirdBook = new Book(UUID.randomUUID(), "name", 2012, "000-0000000002", "publisher_name", 123, author);

        bookRepository.save(book);
        bookRepository.save(secondBook);
        bookRepository.save(thirdBook);
    }

    @Test
    public void getBooksByPartOfNameTest() {
        Assert.assertEquals(3, searchBookAccessService.getBooksByPartOfName("na").size());
    }

    @Test
    public void getBooksByPartOfAuthorNameTest() {
        Assert.assertEquals(3, searchBookAccessService.getBooksByPartOfAuthorName("fi").size());
    }

    @Test
    public void getBooksByIsbnTest() {
        Assert.assertNotNull(searchBookAccessService.getBookByIsbn("000-0000000000"));
    }

    @Test
    public void getBooksByReleaseYearRangeTest() {
        Assert.assertEquals(3, searchBookAccessService.getBooksByReleaseYearRange(2011, 2013).size());
    }

    @Test
    public void getBooksByUserBookmarkTest() {
        User user = new User(UUID.randomUUID(), "user", "pass", false, false);
        Bookmark bookmark = new Bookmark(UUID.randomUUID(), user, book, 123);
        userRepository.save(user);
        bookmarkRepository.save(bookmark);

        Assert.assertEquals(1, searchBookAccessService.getBooksByUserBookmark(user.getId()).size());
    }

    @Test
    public void getBooksBySeveralWaysTest() {
        Assert.assertEquals(3, searchBookAccessService.getBooksBySeveralWays(2012, 123, "name").size());
    }

}
