package com.gosciminski.testsapp.exceptions;

public class QuestionNoTrueAnswerException extends RuntimeException {

    private static final long serialVersionUID = 7644050449763097112L;

    public QuestionNoTrueAnswerException() {
    super("At least one answer must be true");
    }
}