package com.gosciminski.testsapp.controller;

import java.util.List;

import javax.validation.Valid;

import com.gosciminski.testsapp.dto.create.QuestionCreateDto;
import com.gosciminski.testsapp.dto.display.QuestionDisplayDto;
import com.gosciminski.testsapp.dto.edit.QuestionEditDto;
import com.gosciminski.testsapp.exceptions.QuestionException;
import com.gosciminski.testsapp.service.QuestionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "/question")
    public ResponseEntity<List<QuestionDisplayDto>> findAll() {
        return new ResponseEntity<>(questionService.findAllByUser(), HttpStatus.OK);
    }

    @GetMapping(value = "/question/{id}")
    public ResponseEntity<QuestionDisplayDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(questionService.findQuestionDisplayDtoByid(id), HttpStatus.OK);
    }

    @PostMapping(value = "/question")
    public ResponseEntity<QuestionDisplayDto> save(@Valid @RequestBody QuestionCreateDto questionCreateDto)
            throws QuestionException {
        return new ResponseEntity<>(questionService.save(questionCreateDto), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/question/{id}")
    public ResponseEntity<QuestionDisplayDto> update(@PathVariable("id") Long id, @Valid @RequestBody QuestionEditDto questionEditDto) 
    throws QuestionException{
        return new ResponseEntity<>(questionService.update(id, questionEditDto), HttpStatus.OK);
    }

}