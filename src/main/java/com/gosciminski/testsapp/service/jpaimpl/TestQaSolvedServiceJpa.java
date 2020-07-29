package com.gosciminski.testsapp.service.jpaimpl;

import com.gosciminski.testsapp.converter.TestQaSolvedToTestSolvedInfoDto;
import com.gosciminski.testsapp.dto.create.TestSolvedCreateDto;
import com.gosciminski.testsapp.dto.display.TestSolvedInfoDto;
import com.gosciminski.testsapp.exceptions.QuestionNoTrueAnswerException;
import com.gosciminski.testsapp.model.Answer;
import com.gosciminski.testsapp.model.AnsweredAnswer;
import com.gosciminski.testsapp.model.Question;
import com.gosciminski.testsapp.model.QuestionSolved;
import com.gosciminski.testsapp.model.TestQa;
import com.gosciminski.testsapp.model.TestQaSolved;
import com.gosciminski.testsapp.repisitory.TestQaSolvedRepository;
import com.gosciminski.testsapp.service.AnswerService;
import com.gosciminski.testsapp.service.QuestionService;
import com.gosciminski.testsapp.service.TestQaService;
import com.gosciminski.testsapp.service.TestQaSolvedService;

import org.springframework.stereotype.Service;

@Service
public class TestQaSolvedServiceJpa implements TestQaSolvedService {

    private final TestQaSolvedRepository testQaSolvedRepository;
    private final TestQaService testQaService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final TestQaSolvedToTestSolvedInfoDto converter;
    
	public TestQaSolvedServiceJpa(TestQaSolvedRepository testQaSolvedRepository, TestQaService testQaService,
			QuestionService questionService, AnswerService answerService, TestQaSolvedToTestSolvedInfoDto converter) {
		this.testQaSolvedRepository = testQaSolvedRepository;
		this.testQaService = testQaService;
		this.questionService = questionService;
        this.answerService = answerService;
        this.converter = converter;
	}

    @Override
    public TestSolvedInfoDto save(TestSolvedCreateDto source) {

        source.getQuestions().forEach(q -> {
            if(!q.getAnsweredAnswers().stream().anyMatch(a -> a.getCorrect() == true)){
                throw new QuestionNoTrueAnswerException();
            };
        });
        
        TestQaSolved testToSave = new TestQaSolved();
        testToSave.setName(source.getName());
        TestQa testFromDb = testQaService.findByIdAnonymus(source.getId());
        testToSave.setTestQa(testFromDb);
        testFromDb.getTestsQaSolved().add(testToSave);

        source.getQuestions().forEach(
            q -> 
            {
                QuestionSolved questionToSave = new QuestionSolved();
                Question questionFromDb = questionService.findByIdAnonymus(q.getId());
                questionToSave.setQuestion(questionFromDb);
                testToSave.getQuestioSolved().add(questionToSave);
                questionToSave.setTestQaSolved(testToSave);
                questionFromDb.getQuestionSolved().add(questionToSave);

                q.getAnsweredAnswers().forEach(a ->{
                    AnsweredAnswer answerToSave = new AnsweredAnswer();
                    answerToSave.setCorrect(a.getCorrect());
                    Answer answerFromDb = answerService.findById(a.getId());
                    answerToSave.setAnswer(answerFromDb);
                    questionToSave.getAnswerAnswered().add(answerToSave);
                    answerToSave.setQuesionSolved(questionToSave);
                    answerFromDb.getAnsweredAnswers().add(answerToSave);
                });
            }
        );

        TestQaSolved savedSolvedTest = testQaSolvedRepository.save(testToSave);
        testQaService.save(testFromDb);

        TestSolvedInfoDto infoToUser = converter.convert(savedSolvedTest);

        return infoToUser;
    }

}