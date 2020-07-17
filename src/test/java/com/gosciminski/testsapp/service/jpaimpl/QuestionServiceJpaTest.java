package com.gosciminski.testsapp.service.jpaimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import com.gosciminski.testsapp.converter.QuestionCreateDtoToQuestion;
import com.gosciminski.testsapp.converter.QuestionToQuestionDisplayDto;
import com.gosciminski.testsapp.dto.display.QuestionDisplayDto;
import com.gosciminski.testsapp.model.Question;
import com.gosciminski.testsapp.repisitory.QuestionRepository;
import com.gosciminski.testsapp.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionServiceJpaTest {
    
    @Mock
    QuestionRepository questionRepositoryMock;

    @Mock
    QuestionToQuestionDisplayDto questionToQuestionDisplayDtoMock;

    @Mock
    QuestionCreateDtoToQuestion questionCreateDtoToQuestionMock;

    @Mock
    UserService userServiceMock;

    @InjectMocks
    QuestionServiceJpa questionServiceJpa;

    List<Question> questions = new LinkedList<>();

    @BeforeEach
    public void setup(){
        questions.add(new Question());
        questions.add(new Question());
    }

    @Test
    public void findAll_SounldResturnAll(){

        when(questionRepositoryMock.findAll()).thenReturn(questions);
        when(questionToQuestionDisplayDtoMock.convert(any(Question.class))).thenReturn(new QuestionDisplayDto());

        List<QuestionDisplayDto> result = questionServiceJpa.findAll();

        assertNotNull(result);
        assertEquals(result.size(),2);

        
        verify(questionRepositoryMock, atLeastOnce()).findAll();
        verify(questionToQuestionDisplayDtoMock, times(2)).convert(any(Question.class));
    }
}