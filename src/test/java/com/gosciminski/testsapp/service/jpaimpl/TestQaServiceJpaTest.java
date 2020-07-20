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

import com.gosciminski.testsapp.converter.TestQaToTestQaDisplayDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayDto;
import com.gosciminski.testsapp.exceptions.UserNotFoundException;
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
}