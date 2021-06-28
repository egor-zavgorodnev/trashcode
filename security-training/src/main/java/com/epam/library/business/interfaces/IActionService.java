package com.epam.library.business.interfaces;

import com.epam.library.business.exceptions.ActionOperationException;

import java.util.UUID;

public interface IActionService {
    void addNewAction(String message);

    void deleteAction(UUID actionId) throws ActionOperationException;
}
