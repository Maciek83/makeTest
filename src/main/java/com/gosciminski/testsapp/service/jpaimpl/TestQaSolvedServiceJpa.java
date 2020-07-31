package com.gosciminski.testsapp.service.jpaimpl;

import java.util.LinkedList;
import java.util.List;

import com.gosciminski.testsapp.converter.TestQaSolvedToTestSolvedInfoDto;
import com.gosciminski.testsapp.dto.create.TestSolvedCreateDto;
import com.gosciminski.testsapp.dto.display.QuestionSolvedInfoDto;
import com.gosciminski.testsapp.dto.display.TestSolvedInfoDto;
import com.gosciminski.testsapp.exceptions.QuestionNoTrueAnswerException;
import com.gosciminski.testsapp.model.Answer;
import com.gosciminski.testsapp.model.AnsweredAnswer;
import com.gosciminski.testsapp.model.Question;
import com.gosciminski.testsapp.model.QuestionSolved;
import com.gosciminski.testsapp.model.TestQa;
import com.gosciminski.testsapp.model.TestQaShared;
import com.gosciminski.testsapp.model.TestQaSolved;
import com.gosciminski.testsapp.repisitory.TestQaSolvedRepository;
import com.gosciminski.testsapp.service.AnswerService;
import com.gosciminski.testsapp.service.QuestionService;
import com.gosciminski.testsapp.service.TestQaService;
import com.gosciminski.testsapp.service.TestQaShareService;
import com.gosciminski.testsapp.service.TestQaSolvedService;
import com.gosciminski.testsapp.service.UserService;

import org.springframework.stereotype.Service;

@Service
public class TestQaSolvedServiceJpa implements TestQaSolvedService {

    private final TestQaSolvedRepository testQaSolvedRepository;
    private final TestQaService testQaService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final TestQaSolvedToTestSolvedInfoDto converter;
    private final UserService userService;
    private final TestQaShareService testQaShareService;

    public TestQaSolvedServiceJpa(TestQaSolvedRepository testQaSolvedRepository, TestQaService testQaService,
            QuestionService questionService, AnswerService answerService, TestQaSolvedToTestSolvedInfoDto converter,
            UserService userService, TestQaShareService testQaShareService) {
        this.testQaSolvedRepository = testQaSolvedRepository;
        this.testQaService = testQaService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.converter = converter;
        this.userService = userService;
        this.testQaShareService = testQaShareService;
    }

    @Override
    public TestSolvedInfoDto save(TestSolvedCreateDto source) {

        source.getQuestions().forEach(q -> {
            if (!q.getAnsweredAnswers().stream().anyMatch(a -> a.getCorrect() == true)) {
                throw new QuestionNoTrueAnswerException();
            }
            ;
        });

        TestQaShared testSharedFromDb = testQaShareService.findById(source.getTestShareId());

        TestQaSolved testSolvedToSave = new TestQaSolved();
        testSolvedToSave.setPoints(0L);
        testSolvedToSave.setName(source.getName());
        testSolvedToSave.setEmail(source.getEmail());
        testSolvedToSave.setMaxPoints(testSharedFromDb.getPointsToPass());

        TestQa testFromDb = testQaService.findByIdAnonymus(source.getId());
        testSolvedToSave.setTestQa(testFromDb);
        testSolvedToSave.setUser(userService.getUser());
        testFromDb.getTestsQaSolved().add(testSolvedToSave);

        source.getQuestions().forEach(q -> {
            QuestionSolved questionToSave = new QuestionSolved();
            Question questionFromDb = questionService.findByIdAnonymus(q.getId());
            questionToSave.setQuestion(questionFromDb);
            testSolvedToSave.getQuestioSolved().add(questionToSave);
            questionToSave.setTestQaSolved(testSolvedToSave);
            questionFromDb.getQuestionSolved().add(questionToSave);

            q.getAnsweredAnswers().forEach(a -> {
                AnsweredAnswer answerToSave = new AnsweredAnswer();
                answerToSave.setCorrect(a.getCorrect());
                Answer answerFromDb = answerService.findById(a.getId());
                answerToSave.setAnswer(answerFromDb);
                questionToSave.getAnswerAnswered().add(answerToSave);
                answerToSave.setQuesionSolved(questionToSave);
                answerFromDb.getAnsweredAnswers().add(answerToSave);
            });
        });

        testSolvedToSave.getQuestioSolved().forEach(qs -> {

            if(!qs.getAnswerAnswered().stream().filter(aq -> aq.getAnswer().getCorrect() != aq.getCorrect()).findAny().isPresent())
            {
                testSolvedToSave.setPoints(testSolvedToSave.getPoints()+1);
            }

        });

        if(testSolvedToSave.getPoints() >= testSolvedToSave.getMaxPoints()){
            
            testSolvedToSave.setPassed(true);
        }
        else{
            testSolvedToSave.setPassed(false);
        }

        TestQaSolved savedSolvedTest = testQaSolvedRepository.save(testSolvedToSave);
        testQaService.save(testFromDb);

        TestSolvedInfoDto infoToUser = converter.convert(savedSolvedTest);

        return infoToUser;
    }

    @Override
    public List<TestSolvedInfoDto> findAllByUser() {
        List<TestQaSolved> solvedTests = testQaSolvedRepository.findByUser(userService.getUser());
        List<TestSolvedInfoDto> solvedTestsInfoDto = new LinkedList<>();
        solvedTests.forEach(t -> {
            solvedTestsInfoDto.add(converter.convert(t));
        });

        return solvedTestsInfoDto;
    }

}