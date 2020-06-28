package com.gosciminski.testsapp.service;

import java.util.List;

import com.gosciminski.testsapp.dto.create.QuestionCreateDto;
import com.gosciminski.testsapp.dto.display.QuestionDisplayDto;
import com.gosciminski.testsapp.exceptions.QuestionException;
import com.gosciminski.testsapp.model.Question;

public interface QuestionService {
    List<QuestionDisplayDto> findAll();
    Question findById(Long id);
    QuestionDisplayDto save(QuestionCreateDto createDto) throws QuestionException;
}