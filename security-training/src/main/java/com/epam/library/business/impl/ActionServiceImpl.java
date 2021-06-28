package com.epam.library.business.impl;

import com.epam.library.business.exceptions.ActionOperationException;
import com.epam.library.business.exceptions.codes.ErrorCodes;
import com.epam.library.business.interfaces.IActionService;
import com.epam.library.entities.Action;
import com.epam.library.dao.repositories.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActionServiceImpl implements IActionService {

    @Autowired
    ActionRepository actionRepository;

    @Override
    public void addNewAction(String message) {
        actionRepository.save(new Action(UUID.randomUUID(), message));
    }

    @Override
    public void deleteAction(UUID actionId) throws ActionOperationException {
        if (!actionRepository.existsById(actionId)) {
            throw new ActionOperationException(ErrorCodes.ACTION_NOT_FOUND.getCode(), "Действие не найдено");
        }
        actionRepository.deleteById(actionId);
    }
}
