package com.epam.library.dao;

import com.epam.library.dao.interfaces.IHistoryAccessService;
import com.epam.library.entities.History;
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
public class HistoryAccessServiceImplTest {

    @Autowired
    private CrudRepository<History, UUID> historyRepository;

    @Autowired
    private CrudRepository<User, UUID> userRepository;

    @Autowired
    private IHistoryAccessService historyAccessService;

    private User user;
    private History history;

    @BeforeEach
    public void testHistoryInit() {
        user = new User(UUID.randomUUID(), "user","pass", false,false);
        userRepository.save(user);
        history = new History(UUID.randomUUID(), user, "some action");
    }

    @Test
    public void addNewHistoryTest() {

        historyAccessService.addNewEntity(history);

        Assert.assertTrue(historyRepository.existsById(history.getId()));
    }

    @Test
    public void deleteHistoryTest() {

        historyAccessService.deleteEntity(history);

        Assert.assertFalse(historyRepository.existsById(history.getId()));
    }

    @Test
    public void getUserHistoryTest() {
        historyRepository.save(history);

        int actual = historyAccessService.getUserHistory(user).size();

        Assert.assertEquals(1, actual);
    }
}
