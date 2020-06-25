package com.gosciminski.testsapp.service;

import java.util.Set;

import com.gosciminski.testsapp.dto.display.TestQaDisplayDto;



public interface TestQaService {
    Set<TestQaDisplayDto> findAll();
}