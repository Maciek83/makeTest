package com.gosciminski.testsapp.exceptions;

public class TestQaNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -6640619672043435939L;

    public TestQaNotFoundException(Long id) {
        super("Could not find test: " + id);
    }
}