package com.gosciminski.testsapp.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gosciminski.testsapp.dto.display.QuestionDisplayDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayDto;
import com.gosciminski.testsapp.model.Question;
import com.gosciminski.testsapp.model.TestQa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestQaToTestQaDisplayDtoTest {
    
    @Mock
    QuestionToQuestionDisplayDto questiontoQuestionDisplayDto;

    @InjectMocks
    TestQaToTestQaDisplayDto converter;

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
        when(questiontoQuestionDisplayDto.convert(any(Question.class))).thenReturn(new QuestionDisplayDto());

        TestQaDisplayDto result = converter.convert(source);

        assertNotNull(result);
        assertEquals(result.getId(), source.getId());
        assertEquals(result.getName(), source.getName());
        assertEquals(result.getQuestionDisplayDto().size(), source.getQuestions().size());

        verify(questiontoQuestionDisplayDto, atLeastOnce()).convert(any(Question.class));
    }
}