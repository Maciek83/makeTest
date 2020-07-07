package com.gosciminski.testsapp.service.jpaimpl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.gosciminski.testsapp.converter.TestQaToTestQaDisplayDto;
import com.gosciminski.testsapp.dto.create.TestQaCreateDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayDto;
import com.gosciminski.testsapp.exceptions.TestQaException;
import com.gosciminski.testsapp.model.Question;
import com.gosciminski.testsapp.model.TestQa;
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
    public List<TestQaDisplayDto> findAll() {
        Iterable<TestQa> tests = testRepository.findAll();
        List<TestQaDisplayDto> testsDto = new LinkedList<>();
        tests.forEach(t -> testsDto.add(testQaToTestQaDisplayDto.convert(t)));
        return testsDto;
    }

    @Override
    public TestQaDisplayDto save(TestQaCreateDto createDto) throws TestQaException {
        TestQa newTest = new TestQa();
        newTest.setName(createDto.getName());

        Set<Question> questions = new HashSet<>();

        if(createDto.getQuestionsIds().size() <= 0){
            throw new TestQaException("Test must have at least one question.");
        }

        createDto.getQuestionsIds().forEach(i-> 
        {
            Question questionFromDb = questionService.findById(i);
            questionFromDb.getTests().add(newTest);
            questions.add(questionFromDb);
        });
        newTest.setQuestions(questions);

        TestQa savedTest = testRepository.save(newTest);
        return testQaToTestQaDisplayDto.convert(savedTest);
    }
}