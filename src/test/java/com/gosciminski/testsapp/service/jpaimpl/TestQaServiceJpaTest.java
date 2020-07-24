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

import com.gosciminski.testsapp.converter.TestQaToTestQaDisplayDto;
import com.gosciminski.testsapp.dto.create.TestQaCreateDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayDto;
import com.gosciminski.testsapp.dto.edit.TestQaEditDto;
import com.gosciminski.testsapp.exceptions.QuestionNotFoundException;
import com.gosciminski.testsapp.exceptions.TestQaNotFoundException;
import com.gosciminski.testsapp.exceptions.TestQaZeroQuestionsException;
import com.gosciminski.testsapp.exceptions.UserNotFoundException;
import com.gosciminski.testsapp.model.Question;
import com.gosciminski.testsapp.model.TestQa;
import com.gosciminski.testsapp.model.User;
import com.gosciminski.testsapp.repisitory.TestQaRepository;
import com.gosciminski.testsapp.service.QuestionService;
import com.gosciminski.testsapp.service.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestQaServiceJpaTest {
    
    @Mock
    TestQaRepository testRepositoryMock;

    @Mock
    TestQaToTestQaDisplayDto testQaToTestQaDisplayDtoMock;

    @Mock
    QuestionService questionServiceMock;

    @Mock
    UserService userServiceMock;

    @InjectMocks
    TestQaServiceJpa testQaServiceJap;

    @Spy
    @InjectMocks
    TestQaServiceJpa testQaServiceJapSpy;

    List<TestQa> tests = new LinkedList<>();

    @BeforeEach
    public void setup(){
        tests.add(new TestQa());
        tests.add(new TestQa());
    }

    @Test
    public void findAll_shouldFindAll(){

        when(testRepositoryMock.findAll()).thenReturn(tests);
        when(testQaToTestQaDisplayDtoMock.convert(any(TestQa.class))).thenReturn(new TestQaDisplayDto());

        List<TestQaDisplayDto> result = testQaServiceJap.findAll();

        assertNotNull(result);
        assertEquals(tests.size(), result.size());

        verify(testRepositoryMock, atLeastOnce()).findAll();
        verify(testQaToTestQaDisplayDtoMock, times(2)).convert(any(TestQa.class));
    }

    @Test
    public void findAllByUser_shouldFindAll(){

        when(userServiceMock.getUser()).thenReturn(new User());
        when(testRepositoryMock.findByUser(any(User.class))).thenReturn(tests);
        when(testQaToTestQaDisplayDtoMock.convert(any(TestQa.class))).thenReturn(new TestQaDisplayDto());

        List<TestQaDisplayDto> result = testQaServiceJap.findAllByUser();

        assertNotNull(result);
        assertEquals(tests.size(), result.size());

        verify(userServiceMock, atLeastOnce()).getUser();
        verify(testRepositoryMock, atLeastOnce()).findByUser(any(User.class));
        verify(testQaToTestQaDisplayDtoMock, times(2)).convert(any(TestQa.class));
    }

    @Test
    public void findAllByUser_shouldThrowUserNotFoundException(){

        when(userServiceMock.getUser()).thenThrow(UserNotFoundException.class);

        Assertions.assertThrows(UserNotFoundException.class, () ->{
            testQaServiceJap.findAllByUser();
        });
    }

    @Test
    public void save_shouldThrowTestQaZeroQuestionsException(){
        TestQaCreateDto createDto = new TestQaCreateDto();

        Assertions.assertThrows(TestQaZeroQuestionsException.class, () ->{
            testQaServiceJap.save(createDto);
        });

    }

    @Test
    public void save_shoultThrowQuestionNotFoundException(){
        TestQaCreateDto createDto = new TestQaCreateDto();
        createDto.getQuestionsIds().add(1L);
        createDto.getQuestionsIds().add(2L);

        when(questionServiceMock.findById(any(Long.class))).thenThrow(QuestionNotFoundException.class);

        Assertions.assertThrows(QuestionNotFoundException.class, () ->{
            testQaServiceJap.save(createDto);
        });
    }

    @Test
    public void save_shouldSave(){
        TestQaCreateDto createDto = new TestQaCreateDto();
        createDto.getQuestionsIds().add(1L);
        createDto.getQuestionsIds().add(2L);

        when(questionServiceMock.findById(any(Long.class))).thenReturn(new Question());
        when(testRepositoryMock.save(any(TestQa.class))).thenReturn(new TestQa());
        when(userServiceMock.getUser()).thenReturn(new User());
        when(testQaToTestQaDisplayDtoMock.convert(any(TestQa.class))).thenReturn(new TestQaDisplayDto());

        TestQaDisplayDto result = testQaServiceJap.save(createDto);

        assertNotNull(result);

        verify(questionServiceMock, times(2)).findById(any(Long.class));
        verify(testRepositoryMock, atLeastOnce()).save(any(TestQa.class));
        verify(userServiceMock, atLeastOnce()).getUser();
        verify(testQaToTestQaDisplayDtoMock, atLeastOnce()).convert(any(TestQa.class));
    }

    @Test
    public void findById_shouldThrowTestQaNotFoundException(){
        TestQa test = new TestQa();
        test.setId(1L);
        test.setUser(null);
        User user = new User();

        when(testRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(test));
        when(userServiceMock.getUser()).thenReturn(user);

        Assertions.assertThrows(TestQaNotFoundException.class, () -> {
            testQaServiceJap.findById(1L);
        });
    }

    @Test
    public void findById_shouldFind(){
        TestQa test = new TestQa();
        test.setId(1L);
        User user = new User();
        test.setUser(user);

        when(testRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(test));
        when(userServiceMock.getUser()).thenReturn(user);
        
        TestQa result = testQaServiceJap.findById(1L);

        assertNotNull(result);

        verify(testRepositoryMock, atLeastOnce()).findById(any(Long.class));
        verify(userServiceMock, atLeastOnce()).getUser();
    }

    @Test
    public void findTestDisplayDtoById_shouldThrowTestQaNotFoundException(){

        doThrow(TestQaNotFoundException.class).when(testQaServiceJapSpy).findById(1L);

        Assertions.assertThrows(TestQaNotFoundException.class, () -> {
            testQaServiceJapSpy.findTestDisplayDtoById(1L);
        });
    }

    @Test
    public void findTestDisplayDtoById_shouldFind(){

        doReturn(new TestQa()).when(testQaServiceJapSpy).findById(1L);
        when(testQaToTestQaDisplayDtoMock.convert(any(TestQa.class))).thenReturn(new TestQaDisplayDto());

        TestQaDisplayDto result = testQaServiceJapSpy.findTestDisplayDtoById(1L);

        assertNotNull(result);

        verify(testQaServiceJapSpy, atLeastOnce()).findById(any(Long.class));
        verify(testQaToTestQaDisplayDtoMock, atLeastOnce()).convert(any(TestQa.class));
    }

    @Test
    public void update_shouldThrowTestQaNotFoundException(){
        TestQaEditDto editDto = new TestQaEditDto();

        doThrow(TestQaNotFoundException.class).when(testQaServiceJapSpy).findById(1L);

        Assertions.assertThrows(TestQaNotFoundException.class, () -> {
            testQaServiceJapSpy.update(1L, editDto);
        });
    }

    @Test
    public void update_shouldThrowTestQaZeroQuestionsException(){
        TestQaEditDto editDto = new TestQaEditDto();

        doReturn(new TestQa()).when(testQaServiceJapSpy).findById(1L);

        Assertions.assertThrows(TestQaZeroQuestionsException.class, () -> {
            testQaServiceJapSpy.update(1L, editDto);
        });
    }

    @Test
    public void update_shouldThrowQuestionNotFoundException(){
        TestQaEditDto editDto = new TestQaEditDto();
        editDto.getQuestionsIds().add(1L);
        
        doReturn(new TestQa()).when(testQaServiceJapSpy).findById(1L);
        when(questionServiceMock.findById(any(Long.class))).thenThrow(QuestionNotFoundException.class);

        Assertions.assertThrows(QuestionNotFoundException.class, () -> {
            testQaServiceJapSpy.update(1L, editDto);
        });
    }

    @Test
    public void update_ShouldUpdate(){
        TestQaEditDto editDto = new TestQaEditDto();
        editDto.getQuestionsIds().add(1L);
        editDto.getQuestionsIds().add(2L);

        TestQa test = new TestQa();
        Question question = new Question();
        question.setId(3L);
        Question question2 = new Question();
        question2.setId(4L);
        test.getQuestions().add(question);
        test.getQuestions().add(question2);

        doReturn(test).when(testQaServiceJapSpy).findById(1L);
        when(questionServiceMock.findById(any(Long.class))).thenReturn(new Question());
        when(testRepositoryMock.save(any(TestQa.class))).thenReturn(test);
        when(testQaToTestQaDisplayDtoMock.convert(any(TestQa.class))).thenReturn(new TestQaDisplayDto());

        TestQaDisplayDto result = testQaServiceJapSpy.update(1L, editDto);

        assertNotNull(result);

        verify(testQaServiceJapSpy, atLeastOnce()).findById(any(Long.class));
        verify(questionServiceMock, times(4)).findById(any(Long.class));
        verify(testRepositoryMock, atLeastOnce()).save(any(TestQa.class));
        verify(testQaToTestQaDisplayDtoMock, atLeastOnce()).convert(any(TestQa.class));
    }
}