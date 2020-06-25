package com.gosciminski.testsapp.service.jpaimpl;

import java.util.HashSet;
import java.util.Set;

import com.gosciminski.testsapp.converter.TestQaToTestQaDisplayDto;
import com.gosciminski.testsapp.dto.create.TestQaCreateDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayDto;
import com.gosciminski.testsapp.model.Question;
import com.gosciminski.testsapp.model.TestQa;
import com.gosciminski.testsapp.repisitory.QuestionRepository;
import com.gosciminski.testsapp.repisitory.TestQaRepository;
import com.gosciminski.testsapp.service.QuestionService;
import com.gosciminski.testsapp.service.TestQaService;

import org.springframework.stereotype.Service;

@Service
public class TestQaServiceJpa implements TestQaService {

    private final TestQaRepository testRepository;
    private final TestQaToTestQaDisplayDto testQaToTestQaDisplayDto;
    private final QuestionService questionService;

    public TestQaServiceJpa(TestQaRepository testRepository, TestQaToTestQaDisplayDto converter,
    QuestionService questionService) {
    this.testRepository = testRepository;
    this.testQaToTestQaDisplayDto = converter;
    this.questionService = questionService;
    }

    @Override
    public Set<TestQaDisplayDto> findAll() {
        Iterable<TestQa> tests = testRepository.findAll();
        Set<TestQaDisplayDto> testsDto = new HashSet<>();
        tests.forEach(t -> testsDto.add(testQaToTestQaDisplayDto.convert(t)));
        return testsDto;
    }

    @Override
    public TestQaDisplayDto save(TestQaCreateDto createDto) {
        TestQa newTest = new TestQa();
        newTest.setName(createDto.getName());

        Set<Question> questions = new HashSet<>();
        createDto.getQuestionsIds().forEach(i-> questions.add(questionService.findById(i)));
        newTest.setQuestions(questions);

        TestQa savedTest = testRepository.save(newTest);
        return testQaToTestQaDisplayDto.convert(savedTest);
    }




}