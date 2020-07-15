package com.gosciminski.testsapp.service.jpaimpl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.gosciminski.testsapp.converter.TestQaToTestQaDisplayDto;
import com.gosciminski.testsapp.dto.create.TestQaCreateDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayDto;
import com.gosciminski.testsapp.dto.edit.TestQaEditDto;
import com.gosciminski.testsapp.exceptions.TestQaNotFoundException;
import com.gosciminski.testsapp.exceptions.TestQaZeroQuestionsException;
import com.gosciminski.testsapp.model.Question;
import com.gosciminski.testsapp.model.TestQa;
import com.gosciminski.testsapp.repisitory.TestQaRepository;
import com.gosciminski.testsapp.service.QuestionService;
import com.gosciminski.testsapp.service.TestQaService;
import com.gosciminski.testsapp.service.UserService;

import org.springframework.stereotype.Service;

@Service
public class TestQaServiceJpa implements TestQaService {

    private final TestQaRepository testRepository;
    private final TestQaToTestQaDisplayDto testQaToTestQaDisplayDto;
    private final QuestionService questionService;
    private final UserService userService;

    public TestQaServiceJpa(TestQaRepository testRepository, TestQaToTestQaDisplayDto converter,
            QuestionService questionService, UserService userService) {
        this.testRepository = testRepository;
        this.testQaToTestQaDisplayDto = converter;
        this.questionService = questionService;
        this.userService = userService;
    }

    @Override
    public List<TestQaDisplayDto> findAll() {
        Iterable<TestQa> tests = testRepository.findAll();
        List<TestQaDisplayDto> testsDto = new LinkedList<>();
        tests.forEach(t -> testsDto.add(testQaToTestQaDisplayDto.convert(t)));
        return testsDto;
    }

    @Override
    public List<TestQaDisplayDto> findAllByUser() {
        Iterable<TestQa> tests = testRepository.findByUser(userService.getUser());
        List<TestQaDisplayDto> testsDto = new LinkedList<>();
        tests.forEach(t -> testsDto.add(testQaToTestQaDisplayDto.convert(t)));
        return testsDto;
    }

    @Override
    public TestQaDisplayDto save(TestQaCreateDto createDto) throws TestQaZeroQuestionsException {
        TestQa newTest = new TestQa();
        newTest.setName(createDto.getName());

        Set<Question> questions = new HashSet<>();

        if (createDto.getQuestionsIds().size() <= 0) {
            throw new TestQaZeroQuestionsException();
        }

        createDto.getQuestionsIds().forEach(i -> {
            Question questionFromDb = questionService.findById(i);
            questionFromDb.getTests().add(newTest);
            questions.add(questionFromDb);
        });
        newTest.setQuestions(questions);

        TestQa savedTest = testRepository.save(newTest);
        return testQaToTestQaDisplayDto.convert(savedTest);
    }

    @Override
    public TestQa findById(Long id) throws TestQaNotFoundException{
        return testRepository.findById(id)
        .filter(t->t.getUser() == userService.getUser())
        .orElseThrow(()-> new TestQaNotFoundException(id));
    }

    @Override
    public TestQaDisplayDto findTestDisplayDtoById(Long id) {
        return  testQaToTestQaDisplayDto.convert(findById(id));
    }

    @Override
    public TestQaDisplayDto update(Long id, TestQaEditDto editDto) throws TestQaZeroQuestionsException {
        
        TestQa testFromDb = findById(id);
        testFromDb.setName(editDto.getName());

        if (editDto.getQuestionsIds().size() <= 0) {
            throw new TestQaZeroQuestionsException();
        }

        List<Long> listOfIds = editDto.getQuestionsIds()
        .stream()
        .map(ae->ae.longValue()).collect(Collectors.toList());

        List<Long> listOfIdsFromDb = testFromDb.getQuestions()
        .stream()
        .map(q->q.getId().longValue()).collect(Collectors.toList());


        listOfIdsFromDb.forEach(i ->{
            if(!listOfIds.contains(i)){
                Question questionFromDb = questionService.findById(i);
                testFromDb.getQuestions().remove(questionFromDb);
            }
        });

        listOfIds.forEach(i -> {
            if(listOfIdsFromDb.contains(i)){return;}
            Question questionFromDb = questionService.findById(i);
            testFromDb.getQuestions().add(questionFromDb);
        });

        TestQa testAfterSaving = testRepository.save(testFromDb);

        return testQaToTestQaDisplayDto.convert(testAfterSaving);
    }

}