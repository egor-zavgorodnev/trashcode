package com.epam.library.business;

import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.interfaces.IUserService;
import com.epam.library.entities.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

@DataJpaTest
@ComponentScan("com.epam.library")
public class UserServiceImplTest {

    @Autowired
    private CrudRepository<User, UUID> userRepository;

    @Autowired
    private IUserService userService;

    private User user;
    private User lockedUser;

    @BeforeEach
    public void userTestInit() {
        user = new User(UUID.randomUUID(), "user", "pass", false, false);
        lockedUser = new User(UUID.randomUUID(), "u", "p", true, false);
        userRepository.save(user);
        userRepository.save(lockedUser);
    }

    @Test
    public void loginUserTest() throws UserOperationException {
        User existingUser = userService.login("user", "pass");

        Assert.assertNotNull(existingUser);
    }

    @Test
    public void registerUserTest() throws UserOperationException {
        userRepository.deleteAll();
        userService.register("user", "pass");
        Assert.assertEquals(1, userRepository.count());
    }

    @Test
    public void lockUserTest() throws UserOperationException {
        userService.lockUser(user.getId());

        Assert.assertTrue(userRepository.findById(user.getId()).get().isBlocked());
    }

    @Test
    public void unlockUserTest() throws UserOperationException {
        userService.unlockUser(lockedUser.getId());

        Assert.assertFalse(userRepository.findById(lockedUser.getId()).get().isBlocked());
    }


}
