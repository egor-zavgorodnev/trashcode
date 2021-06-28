package com.epam.library.business.interfaces;

import com.epam.library.business.exceptions.HistoryOperationException;
import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.entities.History;

import java.util.List;
import java.util.UUID;

public interface IHistoryService {
    void addHistory(UUID userId, String actionText) throws UserOperationException, HistoryOperationException;

    void deleteHistory(UUID historyId) throws HistoryOperationException;

    List<History> getUserHistory(UUID userId) throws UserOperationException;

}
