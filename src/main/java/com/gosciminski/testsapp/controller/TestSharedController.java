package com.gosciminski.testsapp.controller;

import com.gosciminski.testsapp.dto.display.TestQaDisplayToSolveDto;
import com.gosciminski.testsapp.service.TestQaShareService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestSharedController {

    private final TestQaShareService testQaShareService;

    public TestSharedController(TestQaShareService testQaShareService) {
		this.testQaShareService = testQaShareService;
	}
    
    @PostMapping(value = "/testshare/{id}")
    public ResponseEntity<TestQaDisplayToSolveDto> createTest(@PathVariable("id") Long id) {
        return new ResponseEntity<>(testQaShareService.save(id), HttpStatus.CREATED);
    }
}