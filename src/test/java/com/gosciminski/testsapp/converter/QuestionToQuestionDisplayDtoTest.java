package com.gosciminski.testsapp.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;

import com.gosciminski.testsapp.dto.display.AnswerDisplayDto;
import com.gosciminski.testsapp.dto.display.QuestionDisplayDto;
import com.gosciminski.testsapp.model.Answer;
import com.gosciminski.testsapp.model.Question;
import com.gosciminski.testsapp.model.TestQa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionToQuestionDisplayDtoTest {

    @Mock
    AnswerToAnswerDisplayDto answerToAnswerDisplayDtoMock;

    @InjectMocks
    QuestionToQuestionDisplayDto converter;

    Question source = new Question();

    @BeforeEach
    public void setup(){
        source.setId(1L);
        source.setContent("question");
        
        TestQa test = new TestQa();
        test.setId(1L);

        source.getTests().add(test);
        
        Answer answer1 = new Answer();
        answer1.setId(1L); 
        answer1.setContent("as");
        Answer answer2 = new Answer();
        answer2.setId(2L);
        answer2.setContent("as2");

        Set<Answer> a = source.getAnswers();
        a.add(answer1);
        a.add(answer2);
        
    }

    @Test
    public void convert_shouldConvert(){
        
        when(answerToAnswerDisplayDtoMock.convert(any(Answer.class))).thenReturn(new AnswerDisplayDto());

        QuestionDisplayDto result = converter.convert(source);
        
        assertNotNull(result);
        assertEquals(result.getId(), source.getId());
        assertEquals(result.getContent(), source.getContent());
        assertEquals(result.getTestsIds().size(), source.getTests().size());
        assertEquals(result.getAnswers().size(), 1);

        verify(answerToAnswerDisplayDtoMock, times(2)).convert(any(Answer.class));
    }
}