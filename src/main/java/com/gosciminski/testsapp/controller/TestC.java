package com.gosciminski.testsapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestC {
    @GetMapping(value = "/")
    public String test(){
        return "hello";
    }
}