package com.gosciminski.testsapp.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gosciminski.testsapp.dto.display.QuestionDisplayToSolveDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayToSolveDto;
import com.gosciminski.testsapp.model.Question;
import com.gosciminski.testsapp.model.TestQa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestQaToTestQaDisplayToSolveDtoTest {
    
    @Mock
    QuestionToQuestionDisplayToSolveDto questionToQuestionDisplayToSolveDto;

    @InjectMocks
    TestQaToTestQaDisplayToSolveDto converter;

    TestQa source = new TestQa();

    @BeforeEach
    public void setup(){
        source.setId(1L);
        source.setName("name");

        Question question = new Question();
        source.getQuestions().add(question);
    }

    @Test
    public void convert_shouldConvert(){
        when(questionToQuestionDisplayToSolveDto.convert(any(Question.class))).thenReturn(new QuestionDisplayToSolveDto());

        TestQaDisplayToSolveDto result = converter.convert(source);

        assertNotNull(result);
        assertEquals(result.getId(), source.getId());
        assertEquals(result.getName(), source.getName());

        verify(questionToQuestionDisplayToSolveDto, times(1)).convert(any(Question.class));
    }
}