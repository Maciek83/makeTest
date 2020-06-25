package com.gosciminski.testsapp.service;

import java.util.Set;

import com.gosciminski.testsapp.dto.create.QuestionCreateDto;
import com.gosciminski.testsapp.dto.display.QuestionDisplayDto;
import com.gosciminski.testsapp.model.Question;

public interface QuestionService {
    Set<QuestionDisplayDto> findAll();
    Question findById(Long id);
    QuestionDisplayDto save(QuestionCreateDto createDto);
}