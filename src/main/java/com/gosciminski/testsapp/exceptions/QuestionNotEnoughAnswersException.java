package com.gosciminski.testsapp.exceptions;

public class QuestionNotEnoughAnswersException extends RuntimeException{

    private static final long serialVersionUID = -4257451469113845444L;

    public QuestionNotEnoughAnswersException() {
        super("Not enough answers in question. Add at least two answers.");
    }
}