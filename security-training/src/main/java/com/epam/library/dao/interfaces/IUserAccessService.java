package com.epam.library.dao.interfaces;

import com.epam.library.entities.User;

import java.util.Optional;

public interface IUserAccessService {
    Optional<User> login(String nickname, String password);

    void register(String nickname, String password);

    void unlockUser(User user);

    void lockUser(User user);
}
