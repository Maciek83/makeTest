package com.gosciminski.testsapp.service;

import com.gosciminski.testsapp.dto.create.TestSolvedCreateDto;
import com.gosciminski.testsapp.dto.display.TestSolvedInfoDto;

public interface TestQaSolvedService {
    TestSolvedInfoDto save(TestSolvedCreateDto source);
}