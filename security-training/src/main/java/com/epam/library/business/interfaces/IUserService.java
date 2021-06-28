package com.epam.library.business.interfaces;

import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.entities.User;

import java.util.UUID;

public interface IUserService {
    User login(String nickname, String password) throws UserOperationException;

    void register(String nickname, String password) throws UserOperationException;

    void unlockUser(UUID userId) throws UserOperationException;

    void lockUser(UUID userId) throws UserOperationException;
}
