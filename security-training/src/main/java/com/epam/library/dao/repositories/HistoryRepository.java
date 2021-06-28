package com.epam.library.dao.repositories;

import com.epam.library.entities.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HistoryRepository extends CrudRepository<History, UUID> {
    List<History> findByUserId(UUID userId);
}
