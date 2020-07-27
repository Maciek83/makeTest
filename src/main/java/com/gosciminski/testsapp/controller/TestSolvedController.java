package com.gosciminski.testsapp.controller;

import com.gosciminski.testsapp.dto.create.TestSolvedCreateDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayToSolveDto;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api")
public class TestSolvedController {
    
    @PostMapping(value = "/testsolved")
    public ResponseEntity<TestSolvedCreateDto> solveTest( @RequestBody @Validated TestSolvedCreateDto testSolved) {
        return new ResponseEntity(testSolved, HttpStatus.OK);
    }
}