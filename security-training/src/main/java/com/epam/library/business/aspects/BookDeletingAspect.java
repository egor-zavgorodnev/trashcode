package com.epam.library.business.aspects;

import com.epam.library.business.interfaces.IActionService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BookDeletingAspect {

    @Autowired
    IActionService actionService;


    @AfterReturning("execution (* com.epam.library.business.impl.BookServiceImpl.deleteBook(..))")
    public void handleSuccessDeleteBook(JoinPoint joinPoint) {
        actionService.addNewAction("Book with ID\"" + joinPoint.getArgs()[0].toString() + "\" successfully deleted");
    }

    @AfterThrowing(
            pointcut = "execution (* com.epam.library.business.impl.BookServiceImpl.deleteBook(..))",
            throwing = "ex")
    public void handleErrorDeleteBook(Throwable ex) {
        actionService.addNewAction("Failed to delete book: " + ex.getMessage());
    }

}
