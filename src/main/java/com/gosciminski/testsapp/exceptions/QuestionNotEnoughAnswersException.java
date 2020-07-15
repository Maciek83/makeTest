package com.gosciminski.testsapp.exceptions;

public class QuestionNotEnoughAnswersException extends RuntimeException{
    public QuestionNotEnoughAnswersException(){
        super("Not enough answers in question. Add at least two answers.");
    }
}