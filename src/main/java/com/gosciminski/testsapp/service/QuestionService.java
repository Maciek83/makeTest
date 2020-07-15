package com.gosciminski.testsapp.service;

import java.util.List;

import com.gosciminski.testsapp.dto.create.QuestionCreateDto;
import com.gosciminski.testsapp.dto.display.QuestionDisplayDto;
import com.gosciminski.testsapp.dto.edit.QuestionEditDto;
import com.gosciminski.testsapp.exceptions.QuestionNoTrueAnswerException;
import com.gosciminski.testsapp.exceptions.QuestionNotEnoughAnswersException;
import com.gosciminski.testsapp.exceptions.QuestionNotFoundException;
import com.gosciminski.testsapp.model.Question;

public interface QuestionService {
    List<QuestionDisplayDto> findAll();
    List<QuestionDisplayDto> findAllByUser();
    Question findById(Long id) throws QuestionNotFoundException;
    QuestionDisplayDto findQuestionDisplayDtoByid(Long id);
    QuestionDisplayDto save(QuestionCreateDto createDto) throws QuestionNotEnoughAnswersException, QuestionNoTrueAnswerException;
    QuestionDisplayDto update(Long id, QuestionEditDto editDto) throws QuestionNotEnoughAnswersException, QuestionNoTrueAnswerException;
}