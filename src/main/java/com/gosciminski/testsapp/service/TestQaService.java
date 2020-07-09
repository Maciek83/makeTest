package com.gosciminski.testsapp.service;

import java.util.List;

import com.gosciminski.testsapp.dto.create.TestQaCreateDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayDto;
import com.gosciminski.testsapp.dto.edit.TestQaEditDto;
import com.gosciminski.testsapp.exceptions.TestQaException;
import com.gosciminski.testsapp.model.TestQa;

public interface TestQaService {
    List<TestQaDisplayDto> findAll();
    TestQa findById(Long id);
    TestQaDisplayDto findTestDisplayDtoById(Long id);
    TestQaDisplayDto save(TestQaCreateDto createDto) throws TestQaException;
    TestQaDisplayDto update(Long id, TestQaEditDto editDto) throws TestQaException;
}