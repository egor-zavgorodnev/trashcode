package com.epam.library.dao.impl;

import com.epam.library.dao.interfaces.IHistoryAccessService;
import com.epam.library.dao.repositories.HistoryRepository;
import com.epam.library.entities.History;
import com.epam.library.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryAccessServiceImpl implements IHistoryAccessService {

    @Autowired
	HistoryRepository historyRepository;

    @Override
    public List<History> getUserHistory(User user) {
        return historyRepository.findByUserId(user.getId());
    }

    @Override
    public void addNewEntity(History entity) {
        historyRepository.save(entity);
    }

    @Override
    public void deleteEntity(History entity) {
        historyRepository.delete(entity);
    }
}
