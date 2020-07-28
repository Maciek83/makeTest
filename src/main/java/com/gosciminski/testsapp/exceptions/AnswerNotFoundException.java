package com.gosciminski.testsapp.exceptions;

public class AnswerNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 8736602883312640479L;

    public AnswerNotFoundException(Long id) {
        super("Could not find answer with id: " + id);
    }
}