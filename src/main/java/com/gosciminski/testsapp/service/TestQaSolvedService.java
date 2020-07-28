package com.gosciminski.testsapp.service;

import com.gosciminski.testsapp.dto.create.TestSolvedCreateDto;

public interface TestQaSolvedService {
    void save(TestSolvedCreateDto source);
}