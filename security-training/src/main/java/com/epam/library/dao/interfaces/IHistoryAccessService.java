package com.epam.library.dao.interfaces;

import com.epam.library.entities.History;
import com.epam.library.entities.User;

import java.util.List;

public interface IHistoryAccessService extends IBaseAccessService<History> {
    List<History> getUserHistory(User user);
}
