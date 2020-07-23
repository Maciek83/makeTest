package com.gosciminski.testsapp.service.jpaimpl;

import com.gosciminski.testsapp.converter.TestQaToTestQaDisplayToSolveDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayToSolveDto;
import com.gosciminski.testsapp.model.TestQa;
import com.gosciminski.testsapp.model.TestQaShared;
import com.gosciminski.testsapp.repisitory.TestQaSharedRepository;
import com.gosciminski.testsapp.service.TestQaService;
import com.gosciminski.testsapp.service.TestQaShareService;
import com.gosciminski.testsapp.utils.SercerGenerator;

import org.springframework.stereotype.Service;

@Service
public class TestQaShareServiceJpa implements TestQaShareService{
    private final TestQaSharedRepository testQaSharedRepository;
    private final TestQaService testService;
    private final SercerGenerator sercerGenerator;
    private final TestQaToTestQaDisplayToSolveDto testQaToTestQaDisplayToSolveDto;

    public TestQaShareServiceJpa(TestQaSharedRepository testQaSharedRepository, TestQaService testService,
    SercerGenerator sercerGenerator, TestQaToTestQaDisplayToSolveDto testQaToTestQaDisplayToSolveDto) {
    this.testQaSharedRepository = testQaSharedRepository;
    this.testService = testService;
    this.sercerGenerator = sercerGenerator;
    this.testQaToTestQaDisplayToSolveDto = testQaToTestQaDisplayToSolveDto;
    }

    @Override
    public TestQaDisplayToSolveDto save(Long id) {
        
        TestQa source = testService.findById(id);
        TestQaShared toSave = new TestQaShared();
        toSave.setTest(source);
        toSave.setSecret(sercerGenerator.generateSecret());
        source.setTestQaShared(toSave);

        TestQaShared saved = testQaSharedRepository.save(toSave);
        
        return testQaToTestQaDisplayToSolveDto.convert(saved.getTest());
    }
}