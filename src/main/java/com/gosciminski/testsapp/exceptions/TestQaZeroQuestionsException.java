package com.gosciminski.testsapp.exceptions;

public class TestQaZeroQuestionsException extends RuntimeException {

    private static final long serialVersionUID = 7834622211088493974L;

    public TestQaZeroQuestionsException() {
        super("Test must have at least one question");
    }
}