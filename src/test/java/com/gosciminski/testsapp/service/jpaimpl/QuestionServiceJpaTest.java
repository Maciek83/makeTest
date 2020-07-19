package com.gosciminski.testsapp.service.jpaimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
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
import com.gosciminski.testsapp.dto.edit.AnswerEditDto;
import com.gosciminski.testsapp.dto.edit.QuestionEditDto;
import com.gosciminski.testsapp.exceptions.QuestionNoTrueAnswerException;
import com.gosciminski.testsapp.exceptions.QuestionNotEnoughAnswersException;
import com.gosciminski.testsapp.exceptions.QuestionNotFoundException;
import com.gosciminski.testsapp.exceptions.UserNotFoundException;
import com.gosciminski.testsapp.model.Answer;
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
    public void findById_shouldThrowQuestionNotFoundException(){

        User user = new User();
        Question question = new Question();
        question.setUser(null);
        question.setId(1L);

        when(userServiceMock.getUser()).thenReturn(user);
        when(questionRepositoryMock.findById(1L)).thenReturn(Optional.of(question));

        Assertions.assertThrows(QuestionNotFoundException.class,() -> {
            questionServiceJpa.findById(1L);
        });

        verify(userServiceMock, atLeastOnce()).getUser();
        verify(questionRepositoryMock, atLeastOnce()).findById(1L);
    }

    @Test
    public void findById_shouldFindQuestion(){

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
    public void findQuestionDisplayDtoByid_shouldFindQuestion(){

        doReturn(new Question()).when(questionServiceJpaSpy).findById(1L);
        when(questionToQuestionDisplayDtoMock.convert(any(Question.class))).thenReturn(new QuestionDisplayDto());

        QuestionDisplayDto result = questionServiceJpaSpy.findQuestionDisplayDtoByid(1L);

        assertNotNull(result);
        verify(questionToQuestionDisplayDtoMock, atLeastOnce()).convert(any(Question.class));
    }

    @Test
    public void findQuestionDisplayDtoByid_shouldThrowQuestionNotFoundException(){
        doThrow(QuestionNotFoundException.class).when(questionServiceJpaSpy).findById(1L);

        Assertions.assertThrows(QuestionNotFoundException.class, () -> {
            questionServiceJpaSpy.findQuestionDisplayDtoByid(1L);
        });
    }

    @Test
    public void update_shouldThrowQuestionNotEnoughAnswersException(){

        QuestionEditDto questionEditDto = new QuestionEditDto();
        
        Assertions.assertThrows(QuestionNotEnoughAnswersException.class, () -> {
            questionServiceJpaSpy.update(1L, questionEditDto);
        });

    }

    @Test
    public void update_shouldThrowQuestionNoTrueAnswerException(){
        QuestionEditDto questionEditDto = new QuestionEditDto();
        AnswerEditDto answerEditDto = new AnswerEditDto();
        AnswerEditDto answerEditDto2 = new AnswerEditDto();
        answerEditDto.setCorrect(false);
        answerEditDto2.setCorrect(false);
        questionEditDto.getAnswerEditDto().add(answerEditDto);
        questionEditDto.getAnswerEditDto().add(answerEditDto2);

        Assertions.assertThrows(QuestionNoTrueAnswerException.class, () -> {
            questionServiceJpaSpy.update(1L, questionEditDto);
        });
    }

    @Test
    public void update_shouldThrowQuestionNotFoundException(){
        QuestionEditDto questionEditDto = new QuestionEditDto();
        questionEditDto.setContent("question");
        AnswerEditDto answerEditDto = new AnswerEditDto();
        AnswerEditDto answerEditDto2 = new AnswerEditDto();
        answerEditDto.setCorrect(true);
        answerEditDto2.setCorrect(false);
        questionEditDto.getAnswerEditDto().add(answerEditDto);
        questionEditDto.getAnswerEditDto().add(answerEditDto2);

        doThrow(QuestionNotFoundException.class).when(questionServiceJpaSpy).findById(1L);

        Assertions.assertThrows(QuestionNotFoundException.class, () -> {
            questionServiceJpaSpy.update(1L, questionEditDto);
        });

    }

    @Test
    public void update_shouldUpdateQuestion(){
        QuestionEditDto questionEditDto = new QuestionEditDto();
        questionEditDto.setContent("question");
        AnswerEditDto answerEditDto = new AnswerEditDto();
        AnswerEditDto answerEditDto2 = new AnswerEditDto();
        answerEditDto.setCorrect(true);
        answerEditDto2.setCorrect(false);
        questionEditDto.getAnswerEditDto().add(answerEditDto);
        questionEditDto.getAnswerEditDto().add(answerEditDto2);

        Question question = new Question();
        Answer answer = new Answer();
        answer.setCorrect(true);
        answer.setId(22L);
        Answer answer2 = new Answer();
        answer2.setCorrect(false);
        answer2.setId(23L);
        question.getAnswers().add(answer);
        question.getAnswers().add(answer2);

        doReturn(question).when(questionServiceJpaSpy).findById(1L);
        when(questionRepositoryMock.save(question)).thenReturn(question);
        when(questionToQuestionDisplayDtoMock.convert(question)).thenReturn(new QuestionDisplayDto());

        QuestionDisplayDto result = questionServiceJpaSpy.update(1L, questionEditDto);

        assertNotNull(result);

        verify(questionServiceJpaSpy, atLeastOnce()).findById(1L);
        verify(questionRepositoryMock, atLeastOnce()).save(any(Question.class));
        verify(questionToQuestionDisplayDtoMock, atLeastOnce()).convert(any(Question.class));
    }

    @Test
    public void findAllByUser_shouldThrownUserNotFoundException(){
        when(userServiceMock.getUser()).thenThrow(UserNotFoundException.class);

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            questionServiceJpa.findAllByUser();
        });

        verify(userServiceMock, atLeastOnce()).getUser();
    }

    @Test
    public void findAllByUser_shouldFind(){

        when(userServiceMock.getUser()).thenReturn(new User());
        when(questionRepositoryMock.findByUser(any(User.class))).thenReturn(questions);
        when(questionToQuestionDisplayDtoMock.convert(any(Question.class))).thenReturn(new QuestionDisplayDto());

        List<QuestionDisplayDto> result = questionServiceJpa.findAllByUser();

        assertNotNull(result);
        assertEquals(questions.size(), result.size());

        verify(questionRepositoryMock, atLeastOnce()).findByUser(any(User.class));
        verify(userServiceMock, atLeastOnce()).getUser();
        verify(questionToQuestionDisplayDtoMock, times(2)).convert(any(Question.class));
    }

}