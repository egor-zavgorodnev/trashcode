package com.epam.library.business.impl;

import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.exceptions.BookmarkOperationException;
import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.exceptions.codes.ErrorCodes;
import com.epam.library.business.interfaces.IBookmarkService;
import com.epam.library.entities.Bookmark;
import com.epam.library.dao.interfaces.IBookmarkAccessService;
import com.epam.library.dao.repositories.BookRepository;
import com.epam.library.dao.repositories.BookmarkRepository;
import com.epam.library.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookmarkServiceImpl implements IBookmarkService {

    @Autowired
    IBookmarkAccessService bookmarkAccessService;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addNewBookmark(UUID userId, UUID bookId, int pageNumber) throws BookmarkOperationException, BookOperationException, UserOperationException {
        if (!bookRepository.existsById(bookId)) {
            throw new BookOperationException(ErrorCodes.BOOK_NOT_FOUND.getCode(), "Книги с таким ID не существует");
        }
        if (!userRepository.existsById(userId)) {
            throw new UserOperationException(ErrorCodes.USER_NOT_FOUND.getCode(), "Пользователя с таким ID не существует");
        }
        if (bookRepository.findById(bookId).get().getPageCount() < pageNumber) {
            throw new BookmarkOperationException(ErrorCodes.MORE_PAGES_THAN_IN_THE_BOOK.getCode(), "Страница с закладкой в книге не может быть больше последней страницы");
        }
        if (pageNumber < 1) {
            throw new BookmarkOperationException(ErrorCodes.PAGES_COUNT_LESS_THEN_ONE.getCode(), "Не бывает книги с меньше чем одной страницей");
        }
        Bookmark bookmark = new Bookmark(UUID.randomUUID(), userRepository.findById(userId).get(), bookRepository.findById(bookId).get(), pageNumber);
        bookmarkAccessService.addNewEntity(bookmark);

        if (!bookmarkRepository.existsById(bookmark.getId())) {
            throw new BookmarkOperationException(ErrorCodes.FAILED_TO_ADD_BOOKMARK.getCode(), "Не удалось добавить закладку");
        }
    }

    @Override
    public void deleteBookmark(UUID bookmarkId) throws BookmarkOperationException {
        Optional<Bookmark> bookmarkToBeDeleted = bookmarkRepository.findById(bookmarkId);

        bookmarkToBeDeleted.ifPresent(bm -> bookmarkAccessService.deleteEntity(bm));
        bookmarkToBeDeleted.orElseThrow(() -> new BookmarkOperationException(ErrorCodes.BOOKMARK_NOT_FOUND.getCode(), "Закладки с таким ID не существует"));

        if (bookmarkRepository.existsById(bookmarkId)) {
            throw new BookmarkOperationException(ErrorCodes.FAILED_TO_DELETE_BOOKMARK.getCode(), "Не удалось удалить закладку");
        }
    }

    @Override
    public List<Bookmark> getAllBookmarks() {
        return bookmarkAccessService.getAllEntities();
    }
}
