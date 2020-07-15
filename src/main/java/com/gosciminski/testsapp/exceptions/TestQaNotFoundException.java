package com.gosciminski.testsapp.exceptions;

public class TestQaNotFoundException extends RuntimeException{
    public TestQaNotFoundException(Long id){
        super("Could not find test: " + id);
    }
}