package com.gosciminski.testsapp.controller;

import java.util.List;
import java.util.Set;

import com.gosciminski.testsapp.dto.create.TestQaCreateDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayDto;
import com.gosciminski.testsapp.dto.edit.TestQaEditDto;
import com.gosciminski.testsapp.exceptions.TestQaException;
import com.gosciminski.testsapp.service.TestQaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<List<TestQaDisplayDto>> getAll()
    {
        return new ResponseEntity<>(testQaService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/test/{id}")
    public ResponseEntity<TestQaDisplayDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(testQaService.findTestDisplayDtoById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/test")
    public ResponseEntity<TestQaDisplayDto> createTest(@RequestBody @Validated TestQaCreateDto testQaCreateDto) throws TestQaException{
        return new ResponseEntity<>(testQaService.save(testQaCreateDto), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/test/{id}")
    public ResponseEntity<TestQaDisplayDto> editTest(@PathVariable("id") Long id, @RequestBody TestQaEditDto testEditDto) throws TestQaException{
        return new ResponseEntity<>(testQaService.update(id, testEditDto), HttpStatus.OK);
    }
    
}