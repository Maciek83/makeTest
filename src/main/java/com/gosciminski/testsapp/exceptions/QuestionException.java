package com.gosciminski.testsapp.exceptions;

public class QuestionException extends Exception {

    private static final long serialVersionUID = 716527568871235849L;

    public QuestionException(String data) {
        super("Not enough answers in question. Add at least two answers.");
    }
}