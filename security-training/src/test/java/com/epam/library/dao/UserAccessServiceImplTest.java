package com.epam.library.dao;

import com.epam.library.dao.interfaces.IUserAccessService;
import com.epam.library.entities.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@ComponentScan("com.epam.library")
public class UserAccessServiceImplTest {

    @Autowired
    private CrudRepository<User, UUID> userRepository;

    @Autowired
    private IUserAccessService userAccessService;

    private User user;
    private User lockedUser;

    @BeforeEach
    public void userTestInit() {
        user = new User(UUID.randomUUID(), "user","pass", false,false);
        lockedUser = new User(UUID.randomUUID(), "u","p", true,false);
        userRepository.save(user);
        userRepository.save(lockedUser);
    }

    @Test
    public void loginUserTest() {
        Optional<User> existingUser = userAccessService.login("user","pass");

        Assert.assertTrue(existingUser.isPresent());
    }

    @Test
    public void registerUserTest() {
        userRepository.deleteAll();
        userAccessService.register("user","pass");
        Assert.assertEquals(1, userRepository.count());
    }

    @Test
    public void lockUserTest() {
        userAccessService.lockUser(user);

        Assert.assertTrue(user.isBlocked());
    }

    @Test
    public void unlockUserTest() {
        userAccessService.unlockUser(lockedUser);

        Assert.assertFalse(lockedUser.isBlocked());
    }


}
