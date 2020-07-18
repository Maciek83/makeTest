package com.gosciminski.testsapp.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("There is no logged user");
    }
}