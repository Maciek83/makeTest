package com.gosciminski.testsapp.service;

import java.util.List;

import com.gosciminski.testsapp.dto.display.TestQaDisplayToSolveDto;
import com.gosciminski.testsapp.dto.display.TestQaSharedDisplayDto;

public interface TestQaShareService {
    TestQaDisplayToSolveDto save(Long id);
    List<TestQaSharedDisplayDto> findAllByUser(String appUrl);
}