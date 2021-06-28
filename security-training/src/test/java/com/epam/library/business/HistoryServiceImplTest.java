package com.epam.library.business;

import com.epam.library.business.exceptions.HistoryOperationException;
import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.interfaces.IHistoryService;
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
public class HistoryServiceImplTest {

    @Autowired
    private CrudRepository<History, UUID> historyRepository;

    @Autowired
    private CrudRepository<User, UUID> userRepository;

    @Autowired
    private IHistoryService historyService;

    private User user;
    private History history;

    @BeforeEach
    public void testHistoryInit() {
        user = new User(UUID.randomUUID(), "user", "pass", false, false);
        userRepository.save(user);
        history = new History(UUID.randomUUID(), user, "some action");
        historyRepository.save(history);
    }

    @Test
    public void addNewHistoryTest() throws HistoryOperationException, UserOperationException {

        historyService.addHistory(user.getId(), "some action");

        Assert.assertTrue(historyRepository.existsById(history.getId()));
    }

    @Test
    public void deleteHistoryTest() throws HistoryOperationException {

        historyService.deleteHistory(history.getId());

        Assert.assertFalse(historyRepository.existsById(history.getId()));
    }

    @Test
    public void getUserHistoryTest() throws UserOperationException {
        historyRepository.save(history);

        int actual = historyService.getUserHistory(user.getId()).size();

        Assert.assertEquals(1, actual);
    }
}
