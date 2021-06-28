package com.epam.library.business.impl;

import com.epam.library.business.exceptions.HistoryOperationException;
import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.exceptions.codes.ErrorCodes;
import com.epam.library.business.interfaces.IHistoryService;
import com.epam.library.entities.History;
import com.epam.library.dao.impl.HistoryAccessServiceImpl;
import com.epam.library.dao.repositories.HistoryRepository;
import com.epam.library.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HistoryServiceImpl implements IHistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryAccessServiceImpl historyAccessServiceImpl;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addHistory(UUID userId, String actionText) throws UserOperationException, HistoryOperationException {
        if (!userRepository.existsById(userId)) {
            throw new UserOperationException(ErrorCodes.USER_NOT_FOUND.getCode(), "Пользователя с таким ID не существует");
        }
        History history = new History(UUID.randomUUID(), userRepository.findById(userId).get(), actionText);
        historyAccessServiceImpl.addNewEntity(history);

        if (!historyRepository.existsById(history.getId())) {
            throw new HistoryOperationException(ErrorCodes.FAILED_TO_ADD_HISTORY.getCode(), "Не удалось добавить закладку");
        }
    }

    @Override
    public void deleteHistory(UUID historyId) throws HistoryOperationException {
        Optional<History> historyToBeDeleted = historyRepository.findById(historyId);

        historyToBeDeleted.ifPresent(h -> historyAccessServiceImpl.deleteEntity(h));
        historyToBeDeleted.orElseThrow(() -> new HistoryOperationException(ErrorCodes.HISTORY_NOT_FOUND.getCode(), "История пользователя с таким ID не существует"));

        if (historyRepository.existsById(historyId)) {
            throw new HistoryOperationException(ErrorCodes.FAILED_TO_DELETE_HISTORY.getCode(), "Не удалось удалить историю пользователя");
        }
    }

    @Override
    public List<History> getUserHistory(UUID userId) throws UserOperationException {
        if (!userRepository.existsById(userId)) {
            throw new UserOperationException(ErrorCodes.USER_NOT_FOUND.getCode(), "Пользователя с таким ID не существует");
        }
        return historyAccessServiceImpl.getUserHistory(userRepository.findById(userId).get());
    }
}
