package com.gosciminski.testsapp.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gosciminski.testsapp.dto.create.AnswerCreateDto;
import com.gosciminski.testsapp.dto.create.QuestionCreateDto;
import com.gosciminski.testsapp.model.Answer;
import com.gosciminski.testsapp.model.Question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionCreateDtoToQuestionTest {
    
    @Mock
    AnswerCreateDtoToAnswer answerCreateDtoToAnswerMock;

    @InjectMocks
    QuestionCreateDtoToQuestion converter;

    QuestionCreateDto source = new QuestionCreateDto();

    @BeforeEach
    public void setup(){
        source.setContent("question");

        AnswerCreateDto answer1 = new AnswerCreateDto();
        AnswerCreateDto answer2 = new AnswerCreateDto();

        source.getAnswers().add(answer1);
        source.getAnswers().add(answer2);
    }

    @Test
    public void convert_shouldConvert(){
        when(answerCreateDtoToAnswerMock.convert(any(AnswerCreateDto.class))).thenReturn(new Answer());

        Question result = converter.convert(source);

        assertNotNull(result);
        assertEquals(result.getContent(), source.getContent());
        assertEquals(result.getAnswers().size(), 1);

        verify(answerCreateDtoToAnswerMock, times(2)).convert(any(AnswerCreateDto.class));
    }
}