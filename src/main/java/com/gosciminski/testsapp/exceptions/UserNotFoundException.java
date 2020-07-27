package com.gosciminski.testsapp.exceptions;

public class UserNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -1569647575095009581L;

    public UserNotFoundException() {
        super("There is no logged user");
    }
}