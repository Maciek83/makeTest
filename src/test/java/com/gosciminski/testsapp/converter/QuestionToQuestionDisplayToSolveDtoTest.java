package com.gosciminski.testsapp.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gosciminski.testsapp.dto.display.AnswerDisplayToSolveDto;
import com.gosciminski.testsapp.dto.display.QuestionDisplayToSolveDto;
import com.gosciminski.testsapp.model.Answer;
import com.gosciminski.testsapp.model.Question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionToQuestionDisplayToSolveDtoTest {
    
    @Mock
    AnswerToAnswerDisplayToSolveDto answerToAnswerDisplayToSolveDto;

    @InjectMocks
    QuestionToQuestionDisplayToSolveDto converter;

    Question source = new Question();

    @BeforeEach
    public void setup(){
        source.setId(1L);
        source.setContent("question");

        Answer answer = new Answer();
        source.getAnswers().add(answer);
    }

    @Test
    public void convert_shouldConvert(){

        when(answerToAnswerDisplayToSolveDto.convert(any(Answer.class))).thenReturn(new AnswerDisplayToSolveDto());

        QuestionDisplayToSolveDto result = converter.convert(source);

        assertNotNull(result);
        assertEquals(result.getId(), source.getId());
        assertEquals(result.getContent(), source.getContent());

        verify(answerToAnswerDisplayToSolveDto, times(1)).convert(any(Answer.class));
    }
}