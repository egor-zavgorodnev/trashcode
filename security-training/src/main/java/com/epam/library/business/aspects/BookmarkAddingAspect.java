package com.epam.library.business.aspects;

import com.epam.library.business.exceptions.HistoryOperationException;
import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.interfaces.IActionService;
import com.epam.library.business.interfaces.IHistoryService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class BookmarkAddingAspect {

    @Autowired
	IHistoryService historyService;

    @Autowired
	IActionService actionService;

    @AfterReturning("execution (* com.epam.library.business.impl.BookServiceImpl.addNewBookmark(..))")
    public void handleSuccessAddingBookmark (JoinPoint joinPoint) throws HistoryOperationException, UserOperationException {
        historyService.addHistory(UUID.fromString(joinPoint.getArgs()[0].toString()), "Bookmark for book with ID \"" + joinPoint.getArgs()[0].toString() + "\" successfully added");
    }

    @AfterThrowing(
            pointcut = "execution (* com.epam.library.business.impl.BookServiceImpl.addNewBookmark(..))",
            throwing = "ex")
    public void handleErrorAddingBookmark(Throwable ex) {
        actionService.addNewAction("Failed to add book: " + ex.getMessage());
    }
}
