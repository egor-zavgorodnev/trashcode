package com.epam.library.dao.impl;

import com.epam.library.dao.interfaces.IAuthorAccessService;
import com.epam.library.dao.repositories.AuthorRepository;
import com.epam.library.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorAccessServiceImpl implements IAuthorAccessService {

    @Autowired
	AuthorRepository authorRepository;

    @Override
    public void addNewEntity(Author entity) {
        authorRepository.save(entity);
    }

    @Override
    public void deleteEntity(Author entity) {
        authorRepository.delete(entity);
    }

    @Override
    public List<Author> getAllEntities() {
        return (List<Author>) authorRepository.findAll();
    }
}
