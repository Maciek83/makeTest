package com.gosciminski.testsapp.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class TestQaNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(TestQaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String questionNotFoundHandler(TestQaNotFoundException ex){
        return ex.getMessage();
    }
}