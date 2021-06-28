package com.epam.library.dao.repositories;

import com.epam.library.entities.Bookmark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookmarkRepository extends CrudRepository<Bookmark, UUID> {

}
