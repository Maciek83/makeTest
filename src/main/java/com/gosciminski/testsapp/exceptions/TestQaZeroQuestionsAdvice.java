package com.gosciminski.testsapp.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class TestQaZeroQuestionsAdvice {
    
    @ResponseBody
    @ExceptionHandler(TestQaZeroQuestionsException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String questionNotFoundHandler(TestQaZeroQuestionsException ex){
        return ex.getMessage();
    }
}