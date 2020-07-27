package com.gosciminski.testsapp.exceptions;

public class TestQaSharedNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 6002024498202342008L;

    public TestQaSharedNotFoundException(Long id, String secret) {
        super("Could not find test shared with id: " + id + " and secret " + secret);
    }
}