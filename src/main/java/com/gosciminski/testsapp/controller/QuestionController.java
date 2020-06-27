package com.gosciminski.testsapp.controller;

import java.util.List;
import java.util.Set;

import com.gosciminski.testsapp.dto.create.QuestionCreateDto;
import com.gosciminski.testsapp.dto.display.QuestionDisplayDto;
import com.gosciminski.testsapp.service.QuestionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<QuestionDisplayDto>> findAll()
    {
        return new ResponseEntity<>(questionService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/question")
    public ResponseEntity<QuestionDisplayDto> save(@RequestBody QuestionCreateDto questionCreateDto){        
        return new ResponseEntity<>(questionService.save(questionCreateDto), HttpStatus.CREATED);
    }
}