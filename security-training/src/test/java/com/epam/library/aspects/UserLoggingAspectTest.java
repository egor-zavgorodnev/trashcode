package com.epam.library.aspects;

import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.interfaces.IUserService;
import com.epam.library.dao.repositories.ActionRepository;
import com.epam.library.dao.repositories.HistoryRepository;
import com.epam.library.dao.repositories.UserRepository;
import com.epam.library.entities.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.UUID;

@DataJpaTest
@ComponentScan("com.epam.library")
public class UserLoggingAspectTest {
    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IUserService userService;

    @Test
    public void handleSuccessUserLoginTest() throws UserOperationException {
        User user = new User(UUID.randomUUID(), "user","pass", false,false);
        userRepository.save(user);

        userService.login("user", "pass");

        Assert.assertEquals(1, historyRepository.count());

    }

    @Test
    public void handleErrorUserLoginTest() {
        try {
            userService.login("nonexistent", "user");
        } catch (UserOperationException ignored) {
        }

        Assert.assertEquals(1, actionRepository.count());
    }


}
