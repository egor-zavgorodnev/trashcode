package com.epam.library.dao.repositories;

import com.epam.library.entities.Action;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ActionRepository extends CrudRepository<Action, UUID> {
}
