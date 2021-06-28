package com.epam.library.business;

import com.epam.library.business.exceptions.AuthorOperationException;
import com.epam.library.business.exceptions.DateException;
import com.epam.library.business.interfaces.IAuthorService;
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
public class AuthorServiceImplTest {

    @Autowired
    private CrudRepository<Author, UUID> authorRepository;

    @Autowired
    private CrudRepository<Book, UUID> bookRepository;

    @Autowired
    private IAuthorService authorService;

    private Author author;
    private Book book;

    @BeforeEach
    public void testAuthorInit() {

        author = new Author(UUID.randomUUID(), "first", "second", "last", LocalDate.now());
        authorRepository.save(author);
    }

    @Test
    public void addNewAuthorTest() throws DateException, AuthorOperationException {

        authorService.addNewAuthor("first", "second", "last", LocalDate.now());

        Assert.assertTrue(authorRepository.existsById(author.getId()));
    }

    @Test
    public void deleteAuthorTest() throws AuthorOperationException {

        authorService.deleteAuthorWithHisBooks(author.getId());

        Assert.assertFalse(authorRepository.existsById(author.getId()));
    }

    @Test
    public void findAllAuthorTest() {
        Author secondAuthor = new Author(UUID.randomUUID(), "first", "second", "last", LocalDate.now());
        Author thirdAuthor = new Author(UUID.randomUUID(), "first", "second", "last", LocalDate.now());
        authorRepository.save(author);
        authorRepository.save(secondAuthor);
        authorRepository.save(thirdAuthor);

        int actual = authorService.getAllAuthors().size();

        Assert.assertEquals(3, actual);
    }
}
