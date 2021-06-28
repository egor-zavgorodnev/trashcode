package com.epam.library.business.interfaces;

import com.epam.library.entities.Author;
import com.epam.library.business.exceptions.AuthorOperationException;
import com.epam.library.business.exceptions.DateException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IAuthorService {

    void addNewAuthor(String firstName, String secondName, String lastName, LocalDate dateOfBirth) throws AuthorOperationException, DateException;

    void deleteAuthorWithHisBooks(UUID authorId) throws AuthorOperationException;

    List<Author> getAllAuthors();
}
