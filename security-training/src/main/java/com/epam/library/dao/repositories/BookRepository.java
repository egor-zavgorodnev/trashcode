package com.epam.library.dao.repositories;

import com.epam.library.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<Book, UUID> {
    List<Book> findByBookNameContaining(String bookName);

    List<Book> findByAuthor_nameContaining(String name);

    Book getByISBN(String isbn);

    List<Book> findByReleaseYearBetween(int start, int end);

    List<Book> findByReleaseYearAndPageCountAndBookName(int year, int pageCount, String partOfName);


}
