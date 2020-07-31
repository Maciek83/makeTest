package com.gosciminski.testsapp.service;

import java.util.List;

import com.gosciminski.testsapp.dto.create.ShareTestCreateDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayToSolveDto;
import com.gosciminski.testsapp.dto.display.TestQaSharedDisplayDto;
import com.gosciminski.testsapp.model.TestQaShared;

public interface TestQaShareService {
    TestQaDisplayToSolveDto save(ShareTestCreateDto source);
    List<TestQaSharedDisplayDto> findAllByUser(String appUrl);
    TestQaDisplayToSolveDto findTestToSolve(Long id, String secret);
    TestQaShared findById(Long id);
}