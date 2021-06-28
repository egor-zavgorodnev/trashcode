package com.epam.library.dao.impl;

import com.epam.library.dao.interfaces.IUserAccessService;
import com.epam.library.dao.repositories.UserRepository;
import com.epam.library.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserAccessServiceImpl implements IUserAccessService {

    @Autowired
	UserRepository userRepository;

    @Override
    public Optional<User> login(String nickname, String password) {
        return Optional.ofNullable(userRepository.getByNicknameAndPassword(nickname, password));
    }

    @Override
    public void register(String nickname, String password) {
        userRepository.save(new User(UUID.randomUUID(), nickname, password, false, false));
    }

    @Override
    public void unlockUser(User user) {
        user.setBlocked(false);
        userRepository.save(user);
    }

    @Override
    public void lockUser(User user) {
        user.setBlocked(true);
        userRepository.save(user);
    }
}
