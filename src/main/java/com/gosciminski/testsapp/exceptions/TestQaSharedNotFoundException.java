package com.gosciminski.testsapp.exceptions;

public class TestQaSharedNotFoundException extends RuntimeException{
    public TestQaSharedNotFoundException(Long id, String secret){
        super("Could not find test shared with id: " + id + " and secret " + secret);
    }
}