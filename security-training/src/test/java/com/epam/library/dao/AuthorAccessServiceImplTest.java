package com.epam.library.dao;

import com.epam.library.dao.interfaces.IAuthorAccessService;
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
public class AuthorAccessServiceImplTest {

    @Autowired
    private CrudRepository<Author, UUID> authorRepository;

    @Autowired
    private CrudRepository<Book, UUID> bookRepository;

    @Autowired
    private IAuthorAccessService authorAccessService;

    private Author author;
    private Book book;

    @BeforeEach
    public void testAuthorInit() {
        author = new Author(UUID.randomUUID(), "first", "second", "last", LocalDate.now());
    }

    @Test
    public void addNewAuthorTest() {

        authorAccessService.addNewEntity(author);

        Assert.assertTrue(authorRepository.existsById(author.getId()));
    }

    @Test
    public void deleteAuthorTest() {
        authorRepository.save(author);

        authorAccessService.deleteEntity(author);

        Assert.assertFalse(authorRepository.existsById(author.getId()));
    }

    @Test
    public void findAllAuthorTest() {
        Author secondAuthor = new Author(UUID.randomUUID(), "first", "second", "last", LocalDate.now());
        Author thirdAuthor = new Author(UUID.randomUUID(), "first", "second", "last", LocalDate.now());
        authorRepository.save(author);
        authorRepository.save(secondAuthor);
        authorRepository.save(thirdAuthor);

        int actual = authorAccessService.getAllEntities().size();

        Assert.assertEquals(3, actual);
    }
}
