package com.epam.library.business.interfaces;

import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.exceptions.BookmarkOperationException;
import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.entities.Bookmark;

import java.util.List;
import java.util.UUID;

public interface IBookmarkService {
    void addNewBookmark(UUID userId, UUID bookId, int pageNumber) throws BookOperationException, BookmarkOperationException, UserOperationException;

    void deleteBookmark(UUID bookmarkId) throws BookOperationException, BookmarkOperationException;

    List<Bookmark> getAllBookmarks();
}
