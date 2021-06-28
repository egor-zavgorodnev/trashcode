package com.epam.library.business.impl;

import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.exceptions.codes.ErrorCodes;
import com.epam.library.business.interfaces.IUserService;
import com.epam.library.entities.User;
import com.epam.library.dao.interfaces.IUserAccessService;
import com.epam.library.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserAccessService userAccessService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(String nickname, String password) throws UserOperationException {
        if (userAccessService.login(nickname, password).isPresent()) {
            User user = userAccessService.login(nickname, password).get();
            if (!user.isBlocked()) {
                return user;
            } else {
                throw new UserOperationException(ErrorCodes.USER_IS_BLOCKED.getCode(), "Данный пользователь заблокирован");
            }
        } else {
            throw new UserOperationException(ErrorCodes.USER_NOT_FOUND.getCode(), "Данного пользователя не существует");
        }
    }

    @Override
    public void register(String nickname, String password) throws UserOperationException {

        if (userRepository.getByNickname(nickname) != null) {
            throw new UserOperationException(ErrorCodes.NICKNAME_ALREADY_EXIST.getCode(), "Пользователь с таким никнеймом уже существует");
        }
        userAccessService.register(nickname, password);

    }

    @Override
    public void unlockUser(UUID userId) throws UserOperationException {
        if (!userRepository.existsById(userId)) {
            throw new UserOperationException(ErrorCodes.USER_NOT_FOUND.getCode(), "Пользователя с таким ID не существует");
        }

        User user = userRepository.findById(userId).get();

        userAccessService.unlockUser(user);

        if (user.isBlocked()) {
            throw new UserOperationException(ErrorCodes.FAILED_TO_UNLOCK_USER.getCode(), "Не удалось разблокировать пользователя");
        }

    }

    @Override
    public void lockUser(UUID userId) throws UserOperationException {
        if (!userRepository.existsById(userId)) {
            throw new UserOperationException(ErrorCodes.USER_NOT_FOUND.getCode(), "Пользователя с таким ID не существует");
        }

        User user = userRepository.findById(userId).get();

        userAccessService.lockUser(user);

        if (!user.isBlocked()) {
            throw new UserOperationException(ErrorCodes.FAILED_TO_LOCK_USER.getCode(), "Не удалось заблокировать пользователя");
        }
    }
}
