package com.epam.library.aspects;


import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.interfaces.IBookService;
import com.epam.library.dao.repositories.AuthorRepository;
import com.epam.library.dao.repositories.BookRepository;
import com.epam.library.entities.Action;
import com.epam.library.entities.Author;
import com.epam.library.entities.Book;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.UUID;

;

@DataJpaTest
@ComponentScan("com.epam.library")
public class BookDeletingAspectTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
	CrudRepository<Action,UUID> actionRepository;

    @Autowired
    private IBookService bookService;


    @Test
    public void handleSuccessDeleteBookTest() throws BookOperationException {
        Author author = new Author(UUID.randomUUID(), "first", "second", "last", LocalDate.now());
        Book book = new Book(UUID.randomUUID(), "name", 2012, "000-0000000000", "publisher_name", 123, author);
        authorRepository.save(author);
        bookRepository.save(book);

        bookService.deleteBook(book.getId());

        Assert.assertEquals(1,actionRepository.count());
    }

    @Test
    public void handleErrorDeleteBook()  {
        try {
            bookService.deleteBook(UUID.randomUUID());
        } catch (BookOperationException ignored) {
        }

        Assert.assertEquals(1,actionRepository.count());
    }
}
