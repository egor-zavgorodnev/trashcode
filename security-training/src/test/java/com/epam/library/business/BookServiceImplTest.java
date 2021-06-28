package com.epam.library.business;

import com.epam.library.business.exceptions.AuthorOperationException;
import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.exceptions.DateException;
import com.epam.library.business.interfaces.IBookService;
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
public class BookServiceImplTest {
    @Autowired
    private CrudRepository<Book, UUID> bookRepository;

    @Autowired
    private CrudRepository<Author, UUID> authorCrudRepository;

    @Autowired
    private IBookService bookService;

    private Book book;
    private Author author;

    @BeforeEach
    public void testBookInit() {
        author = new Author(UUID.randomUUID(), "first", "second", "last", LocalDate.now());
        authorCrudRepository.save(author);
        book = new Book(UUID.randomUUID(), "name", 2012, "000-0000000000", "publisher_name", 123, author);
        bookRepository.save(book);
    }

    @Test
    public void addNewBookTest() throws DateException, BookOperationException, AuthorOperationException {

        bookService.addNewBook("name", 2012, "000-0000000123", "publisher_name", 123, author.getId());

        Assert.assertTrue(bookRepository.existsById(book.getId()));
    }

    @Test
    public void deleteBookTest() throws BookOperationException {

        bookService.deleteBook(book.getId());

        Assert.assertFalse(bookRepository.existsById(book.getId()));
    }

    @Test
    public void findAllBookTest() {
        Book secondBook = new Book(UUID.randomUUID(), "name", 2012, "000-0000000000", "publisher_name", 123, author);
        Book thirdBook = new Book(UUID.randomUUID(), "name", 2012, "000-0000000000", "publisher_name", 123, author);
        bookRepository.save(book);
        bookRepository.save(secondBook);
        bookRepository.save(thirdBook);

        int actual = bookService.getAllBooks().size();

        Assert.assertEquals(3, actual);
    }
}
