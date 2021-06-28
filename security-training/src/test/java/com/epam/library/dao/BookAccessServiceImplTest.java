package com.epam.library.dao;

import com.epam.library.dao.interfaces.IBookAccessService;
import com.epam.library.entities.Author;
import com.epam.library.entities.Book;
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
public class BookAccessServiceImplTest {
    @Autowired
    private CrudRepository<Book, UUID> bookRepository;

    @Autowired
    private CrudRepository<Author, UUID> authorCrudRepository;

    @Autowired
    private IBookAccessService bookAccessService;

    private Book book;
    private Author author;

    @BeforeEach
    public void testBookInit() {
        author = new Author(UUID.randomUUID(), "first", "second", "last", LocalDate.now());
        authorCrudRepository.save(author);
        book = new Book(UUID.randomUUID(), "name", 2012, "000-0000000000", "publisher_name", 123, author);
    }

    @Test
    public void addNewBookTest() {

        bookAccessService.addNewEntity(book);

        Assert.assertTrue(bookRepository.existsById(book.getId()));
    }

    @Test
    public void deleteBookTest() {

        bookAccessService.deleteEntity(book);

        Assert.assertFalse(bookRepository.existsById(book.getId()));
    }

    @Test
    public void findAllBookTest() {
        Book secondBook = new Book(UUID.randomUUID(), "name", 2012, "000-0000000000", "publisher_name", 123, author);
        Book thirdBook = new Book(UUID.randomUUID(), "name", 2012, "000-0000000000", "publisher_name", 123, author);
        bookRepository.save(book);
        bookRepository.save(secondBook);
        bookRepository.save(thirdBook);

        int actual = bookAccessService.getAllEntities().size();

        Assert.assertEquals(3, actual);
    }
}
