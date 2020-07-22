package com.gosciminski.testsapp.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.gosciminski.testsapp.dto.create.AnswerCreateDto;
import com.gosciminski.testsapp.model.Answer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnswerCreatorDtoToAnswerTest {
    
    AnswerCreateDtoToAnswer converter = new AnswerCreateDtoToAnswer();

    AnswerCreateDto source = new AnswerCreateDto();

    @BeforeEach
    public void setup(){
        source.setContent("answer");
        source.setCorrect(true);
    }

    @Test
    public void convert_shouldConvert(){

        Answer result = converter.convert(source);

        assertNotNull(result);
        assertEquals(result.getContent(), source.getContent());
        assertEquals(result.getCorrect(), source.getCorrect());
    }
}