package com.gosciminski.testsapp.exceptions;

public class QuestionNotFoundException extends RuntimeException {
    
    public QuestionNotFoundException(Long id){
        super("Could not find question with id: " + id);
    }
}