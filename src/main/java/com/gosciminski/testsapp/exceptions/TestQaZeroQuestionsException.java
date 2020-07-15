package com.gosciminski.testsapp.exceptions;

public class TestQaZeroQuestionsException extends RuntimeException {
    public TestQaZeroQuestionsException(){
        super("Test must have at least one question");
    }
}