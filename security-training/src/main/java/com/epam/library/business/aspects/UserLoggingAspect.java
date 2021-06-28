package com.epam.library.business.aspects;

import com.epam.library.business.exceptions.HistoryOperationException;
import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.interfaces.IActionService;
import com.epam.library.business.interfaces.IHistoryService;
import com.epam.library.entities.User;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserLoggingAspect {

    @Autowired
	IHistoryService historyService;

    @Autowired
	IActionService actionService;

    @AfterReturning(
            pointcut = "execution (* com.epam.library.business.impl.UserServiceImpl.login(..))",
            returning = "retVal")
    public void handleSuccessUserLogin(Object retVal) throws HistoryOperationException, UserOperationException {
        User user = (User) retVal;
        historyService.addHistory(user.getId(), user.getNickname() + " logged in successfully");
    }

    @AfterThrowing(
            pointcut = "execution (* com.epam.library.business.impl.UserServiceImpl.login(..))",
            throwing = "ex")
    public void handleErrorUserLogin(Throwable ex) {
        actionService.addNewAction("User cannot logged: " + ex.getMessage());
    }

}
