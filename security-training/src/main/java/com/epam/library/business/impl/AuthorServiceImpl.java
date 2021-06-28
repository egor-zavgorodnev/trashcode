package com.epam.library.business.impl;

import com.epam.library.business.exceptions.AuthorOperationException;
import com.epam.library.business.exceptions.DateException;
import com.epam.library.business.exceptions.codes.ErrorCodes;
import com.epam.library.business.interfaces.IAuthorService;
import com.epam.library.entities.Author;
import com.epam.library.dao.interfaces.IAuthorAccessService;
import com.epam.library.dao.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements IAuthorService {

    @Autowired
    private IAuthorAccessService authorAccessService;

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public void addNewAuthor(String firstName, String secondName, String lastName, LocalDate dateOfBirth) throws AuthorOperationException, DateException {
        if (dateOfBirth.getYear() > LocalDate.now().getYear()) {
            throw new DateException(ErrorCodes.DATE_IS_GREATER_THAN_THE_CURRENT.getCode(), "Дата больше текущей");
        }
        Author author = new Author(UUID.randomUUID(), firstName, secondName, lastName, dateOfBirth);
        authorAccessService.addNewEntity(author);

        if (!authorRepository.existsById(author.getId())) {
            throw new AuthorOperationException(ErrorCodes.FAILED_TO_ADD_AUTHOR.getCode(), "Не удалось добавить автора");
        }
    }

    @Override
    public void deleteAuthorWithHisBooks(UUID authorId) throws AuthorOperationException {
        Optional<Author> authorToBeDeleted = authorRepository.findById(authorId);

        authorToBeDeleted.ifPresent(a -> authorAccessService.deleteEntity(a));
        authorToBeDeleted.orElseThrow(() -> new AuthorOperationException(ErrorCodes.AUTHOR_NOT_FOUND.getCode(), "Автора с таким ID не существует"));

        if (authorRepository.existsById(authorId)) {
            throw new AuthorOperationException(ErrorCodes.FAILED_TO_DELETE_AUTHOR.getCode(), "Не удалось удалить автора");
        }

    }

    @Override
    public List<Author> getAllAuthors() {
        return authorAccessService.getAllEntities();
    }
}
