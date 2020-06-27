package com.gosciminski.testsapp.service.jpaimpl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.gosciminski.testsapp.converter.QuestionCreateDtoToQuestion;
import com.gosciminski.testsapp.converter.QuestionToQuestionDisplayDto;
import com.gosciminski.testsapp.dto.create.QuestionCreateDto;
import com.gosciminski.testsapp.dto.display.QuestionDisplayDto;
import com.gosciminski.testsapp.model.Question;
import com.gosciminski.testsapp.repisitory.QuestionRepository;
import com.gosciminski.testsapp.service.QuestionService;

import org.springframework.stereotype.Service;

@Service
public class QuestionServiceJpa implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionToQuestionDisplayDto questionToQuestionDisplayDto;
    private final QuestionCreateDtoToQuestion questionCreateDtoToQuestion;

    public QuestionServiceJpa(QuestionRepository questionRepository,
    QuestionToQuestionDisplayDto questionToQuestionDisplayDto,
    QuestionCreateDtoToQuestion questionCreateDtoToQuestion) {
    this.questionRepository = questionRepository;
    this.questionToQuestionDisplayDto = questionToQuestionDisplayDto;
    this.questionCreateDtoToQuestion = questionCreateDtoToQuestion;
    }

    @Override
    public List<QuestionDisplayDto> findAll() {
        Iterable<Question> questions = questionRepository.findAll();
        List<QuestionDisplayDto> questionsDisplayDto = new LinkedList<>();
        questions.forEach(q->questionsDisplayDto.add(questionToQuestionDisplayDto.convert(q)));
        return questionsDisplayDto;
    }

    @Override
    public QuestionDisplayDto save(QuestionCreateDto createDto) {
        Question tosave = questionCreateDtoToQuestion.convert(createDto);
        Question savedInDb = questionRepository.save(tosave);
        return questionToQuestionDisplayDto.convert(savedInDb);
    }

    @Override
    public Question findById(Long id) {     
        return questionRepository.findById(id).orElseThrow();
    }

 
    
}