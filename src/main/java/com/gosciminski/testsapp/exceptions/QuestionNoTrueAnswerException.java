package com.gosciminski.testsapp.exceptions;

public class QuestionNoTrueAnswerException extends RuntimeException {

    public QuestionNoTrueAnswerException(){
    super("At least one answer must be true");
    }
}