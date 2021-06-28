package com.epam.library.dao.impl;

import com.epam.library.dao.interfaces.ISearchBookAccessService;
import com.epam.library.dao.repositories.BookRepository;
import com.epam.library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

@Service
public class SearchBookAccessServiceImpl implements ISearchBookAccessService {

    @Autowired
	BookRepository bookRepository;

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Book> getBooksByPartOfName(String partOfName) {
        return bookRepository.findByBookNameContaining(partOfName);
    }

    @Override
    public List<Book> getBooksByPartOfAuthorName(String partOfAuthorName) {
        return bookRepository.findByAuthor_nameContaining(partOfAuthorName);
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return bookRepository.getByISBN(isbn);
    }

    @Override
    public List<Book> getBooksByReleaseYearRange(int startYear, int endYear) {
        return bookRepository.findByReleaseYearBetween(startYear, endYear);
    }

    @Override
    public List<Book> getBooksByUserBookmark(UUID userId) {
        TypedQuery<Book> query = entityManager.createQuery(
                "SELECT b FROM Book b WHERE b.id IN (SELECT bm.book FROM Bookmark bm WHERE bm.user.id = ?1)", Book.class);
        query.setParameter(1, userId);

        return query.getResultList();
    }

    @Override
    public List<Book> getBooksBySeveralWays(int year, int pageCount, String name) {
        return bookRepository.findByReleaseYearAndPageCountAndBookName(year, pageCount, name);
    }
}
