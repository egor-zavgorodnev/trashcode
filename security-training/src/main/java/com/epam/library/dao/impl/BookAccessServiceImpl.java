package com.epam.library.dao.impl;

import com.epam.library.dao.interfaces.IBookAccessService;
import com.epam.library.dao.repositories.BookRepository;
import com.epam.library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAccessServiceImpl implements IBookAccessService {

    @Autowired
	BookRepository bookRepository;

    @Override
    public void addNewEntity(Book entity) {
        bookRepository.save(entity);
    }

    @Override
    public void deleteEntity(Book entity) {
        bookRepository.delete(entity);
    }

    @Override
    public List<Book> getAllEntities() {
        return (List<Book>) bookRepository.findAll();
    }
}
