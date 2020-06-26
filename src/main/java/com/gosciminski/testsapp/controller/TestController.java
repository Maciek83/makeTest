package com.gosciminski.testsapp.controller;

import java.util.Set;

import com.gosciminski.testsapp.dto.create.TestQaCreateDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayDto;
import com.gosciminski.testsapp.service.TestQaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    private final TestQaService testQaService;

    public TestController(TestQaService testQaService) {
        this.testQaService = testQaService;
    }

    @GetMapping(value = "/test")
    public ResponseEntity<Set<TestQaDisplayDto>> getAll()
    {
        return new ResponseEntity<>(testQaService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/test")
    public ResponseEntity<TestQaDisplayDto> createTest(@RequestBody TestQaCreateDto testQaCreateDto){
        return new ResponseEntity<>(testQaService.save(testQaCreateDto), HttpStatus.CREATED);
    }
    
}