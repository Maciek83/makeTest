package com.gosciminski.testsapp.service.jpaimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.gosciminski.testsapp.converter.QuestionCreateDtoToQuestion;
import com.gosciminski.testsapp.converter.QuestionToQuestionDisplayDto;
import com.gosciminski.testsapp.dto.create.AnswerCreateDto;
import com.gosciminski.testsapp.dto.create.QuestionCreateDto;
import com.gosciminski.testsapp.dto.display.QuestionDisplayDto;
import com.gosciminski.testsapp.exceptions.QuestionNoTrueAnswerException;
import com.gosciminski.testsapp.exceptions.QuestionNotEnoughAnswersException;
import com.gosciminski.testsapp.exceptions.QuestionNotFoundException;
import com.gosciminski.testsapp.model.Question;
import com.gosciminski.testsapp.model.User;
import com.gosciminski.testsapp.repisitory.QuestionRepository;
import com.gosciminski.testsapp.service.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
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

    @Spy
    @InjectMocks
    QuestionServiceJpa questionServiceJpaSpy;

    List<Question> questions = new LinkedList<>();

    @BeforeEach
    public void setup(){
        questions.add(new Question());
        questions.add(new Question());
    }

    @Test
    public void findAll_ShouldReturnAll(){

        when(questionRepositoryMock.findAll()).thenReturn(questions);
        when(questionToQuestionDisplayDtoMock.convert(any(Question.class))).thenReturn(new QuestionDisplayDto());

        List<QuestionDisplayDto> result = questionServiceJpa.findAll();

        assertNotNull(result);
        assertEquals(result.size(),2);

        
        verify(questionRepositoryMock, atLeastOnce()).findAll();
        verify(questionToQuestionDisplayDtoMock, times(2)).convert(any(Question.class));
    }

    @Test
    public void save_ShoultThrowQuestionNotEnoughAnswersException(){
        QuestionCreateDto questionCreateDto= new QuestionCreateDto();
        questionCreateDto.setContent("question");
        AnswerCreateDto answer = new AnswerCreateDto();
        answer.setContent("answer");
        answer.setCorrect(true);
        questionCreateDto.getAnswers().add(answer);

        Assertions.assertThrows(QuestionNotEnoughAnswersException.class, () ->{
            questionServiceJpa.save(questionCreateDto);
        });
    }

    @Test
    public void save_ShouldThrowQuestionNoTrueAnswerException(){
        QuestionCreateDto questionCreateDto= new QuestionCreateDto();
        questionCreateDto.setContent("question");
        AnswerCreateDto answer = new AnswerCreateDto();
        answer.setContent("answer");
        answer.setCorrect(false);
        AnswerCreateDto answer2 = new AnswerCreateDto();
        answer2.setContent("answer2");
        answer2.setCorrect(false);
        questionCreateDto.getAnswers().add(answer);
        questionCreateDto.getAnswers().add(answer2);

        Assertions.assertThrows(QuestionNoTrueAnswerException.class, () ->{
            questionServiceJpa.save(questionCreateDto);
        });
    }

    @Test
    public void save_ShouldSave(){

        QuestionCreateDto questionCreateDto = new QuestionCreateDto();
        questionCreateDto.setContent("question");
        AnswerCreateDto answer = new AnswerCreateDto();
        answer.setContent("answer");
        answer.setCorrect(false);
        AnswerCreateDto answer2 = new AnswerCreateDto();
        answer2.setContent("answer2");
        answer2.setCorrect(true);
        questionCreateDto.getAnswers().add(answer);
        questionCreateDto.getAnswers().add(answer2);

        when(questionCreateDtoToQuestionMock.convert(any(QuestionCreateDto.class))).thenReturn(new Question());
        when(userServiceMock.getUser()).thenReturn(new User());
        when(questionRepositoryMock.save(any(Question.class))).thenReturn(new Question());
        when(questionToQuestionDisplayDtoMock.convert(any(Question.class))).thenReturn(new QuestionDisplayDto());

        QuestionDisplayDto result = questionServiceJpa.save(questionCreateDto);

        assertNotNull(result);

        verify(questionCreateDtoToQuestionMock, atLeastOnce()).convert(any(QuestionCreateDto.class));
        verify(userServiceMock, atLeastOnce()).getUser();
        verify(questionRepositoryMock, atLeastOnce()).save(any(Question.class));
        verify(questionToQuestionDisplayDtoMock, atLeastOnce()).convert(any(Question.class));
    }

    @Test
    public void findById_ShouldThrowQuestionNotFoundException(){

        User user = new User();
        Question question = new Question();
        question.setUser(null);
        question.setId(1L);

        when(userServiceMock.getUser()).thenReturn(user);
        when(questionRepositoryMock.findById(1L)).thenReturn(Optional.of(question));

        Assertions.assertThrows(QuestionNotFoundException.class,()->{
            questionServiceJpa.findById(1L);
        });

        verify(userServiceMock, atLeastOnce()).getUser();
        verify(questionRepositoryMock, atLeastOnce()).findById(1L);
    }

    @Test
    public void findById_ShouldFindQuestion(){

        User user = new User();
        Question question = new Question();
        question.setId(1L);
        question.setUser(user);

        when(userServiceMock.getUser()).thenReturn(user);
        when(questionRepositoryMock.findById(1L)).thenReturn(Optional.of(question));

        Question result = questionServiceJpa.findById(1L);

        assertNotNull(result);
        assertEquals(question.getId(), result.getId());
        assertEquals(question.getUser(), result.getUser());

        verify(userServiceMock, atLeastOnce()).getUser();
        verify(questionRepositoryMock, atLeastOnce()).findById(1L);
    }

    @Test
    public void findQuestionDisplayDtoByid_shouldFind(){

        doReturn(new Question()).when(questionServiceJpaSpy).findById(1L);
        when(questionToQuestionDisplayDtoMock.convert(any(Question.class))).thenReturn(new QuestionDisplayDto());

        QuestionDisplayDto result = questionServiceJpaSpy.findQuestionDisplayDtoByid(1L);

        assertNotNull(result);
        verify(questionToQuestionDisplayDtoMock, atLeastOnce()).convert(any(Question.class));
    }

}