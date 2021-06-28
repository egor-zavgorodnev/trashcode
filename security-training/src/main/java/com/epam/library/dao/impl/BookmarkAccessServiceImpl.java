package com.epam.library.dao.impl;

import com.epam.library.dao.interfaces.IBookmarkAccessService;
import com.epam.library.dao.repositories.BookmarkRepository;
import com.epam.library.entities.Bookmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkAccessServiceImpl implements IBookmarkAccessService {

    @Autowired
	BookmarkRepository bookmarkRepository;

    @Override
    public void addNewEntity(Bookmark bookmark) {
        bookmarkRepository.save(bookmark);
    }

    @Override
    public void deleteEntity(Bookmark bookmark) {
        bookmarkRepository.delete(bookmark);
    }

    @Override
    public List<Bookmark> getAllEntities() {
        return (List<Bookmark>) bookmarkRepository.findAll();
    }
}
