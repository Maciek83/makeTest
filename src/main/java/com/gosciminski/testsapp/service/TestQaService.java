package com.gosciminski.testsapp.service;

import java.util.Set;

import com.gosciminski.testsapp.dto.create.TestQaCreateDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayDto;
import com.gosciminski.testsapp.exceptions.TestQaException;

public interface TestQaService {
    Set<TestQaDisplayDto> findAll();
    TestQaDisplayDto save(TestQaCreateDto createDto) throws TestQaException;
}