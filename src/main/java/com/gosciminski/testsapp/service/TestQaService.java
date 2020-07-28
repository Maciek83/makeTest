package com.gosciminski.testsapp.service;

import java.util.List;

import com.gosciminski.testsapp.dto.create.TestQaCreateDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayDto;
import com.gosciminski.testsapp.dto.edit.TestQaEditDto;
import com.gosciminski.testsapp.exceptions.TestQaNotFoundException;
import com.gosciminski.testsapp.exceptions.TestQaZeroQuestionsException;
import com.gosciminski.testsapp.model.TestQa;

public interface TestQaService {
    List<TestQaDisplayDto> findAll();
    List<TestQaDisplayDto> findAllByUser();
    TestQa findById(Long id) throws TestQaNotFoundException;
    TestQa findByIdAnonymus(Long id) throws TestQaNotFoundException;
    TestQaDisplayDto findTestDisplayDtoById(Long id);
    TestQaDisplayDto save(TestQaCreateDto createDto) throws TestQaZeroQuestionsException;
    TestQa save(TestQa testQa);
    TestQaDisplayDto update(Long id, TestQaEditDto editDto) throws TestQaZeroQuestionsException;
}