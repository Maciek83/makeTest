package com.gosciminski.testsapp.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.gosciminski.testsapp.dto.display.AnswerDisplayDto;
import com.gosciminski.testsapp.model.Answer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnswerToAnswerDisplayDtoTest {

    AnswerToAnswerDisplayDto converter = new AnswerToAnswerDisplayDto();

    Answer source = new Answer();

    @BeforeEach
    public void setup(){
        source.setId(1L);
        source.setContent("answer");
        source.setCorrect(true);
    }

    @Test
    public void convert_shouldConvert(){
        AnswerDisplayDto result = converter.convert(source);

        assertNotNull(result);
        assertEquals(result.getId(), source.getId());
        assertEquals(result.getContent(), source.getContent());
        assertEquals(result.getCorrect(), source.getCorrect());
    }
}