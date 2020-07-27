package com.gosciminski.testsapp.exceptions;

public class QuestionNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7983480471317389542L;

    public QuestionNotFoundException(Long id) {
        super("Could not find question with id: " + id);
    }
}