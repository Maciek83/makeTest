package com.gosciminski.testsapp.controller;

import java.util.List;

import com.gosciminski.testsapp.dto.create.TestSolvedCreateDto;
import com.gosciminski.testsapp.dto.display.TestSolvedInfoDto;
import com.gosciminski.testsapp.service.TestQaSolvedService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestSolvedController {

    private final TestQaSolvedService testQaSolvedService;
    
    public TestSolvedController(TestQaSolvedService testQaSolvedService) {
		this.testQaSolvedService = testQaSolvedService;
    }
    
    @GetMapping(value = "/testsolved")
    public ResponseEntity<List<TestSolvedInfoDto>> getSolvedTests(){
        return new ResponseEntity<>(testQaSolvedService.findAllByUser(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/testsolved")
    public ResponseEntity<TestSolvedInfoDto> solveTest(@RequestBody @Validated TestSolvedCreateDto testSolved) {
        return new ResponseEntity<>(testQaSolvedService.save(testSolved), HttpStatus.CREATED);
    }
	
}