package com.gosciminski.testsapp.exceptions;

public class PointsException extends RuntimeException{

    private static final long serialVersionUID = 5429379027195506923L;

    public PointsException() {
        super("To many points for test or points are less than zero");
    }
}