package com.gosciminski.testsapp.converter;

import com.gosciminski.testsapp.dto.create.AnswerCreateDto;
import com.gosciminski.testsapp.model.Answer;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AnswerCreateDtoToAnswer implements Converter<AnswerCreateDto, Answer> {

    @Override
    public Answer convert(AnswerCreateDto source) {
        Answer newAnswer = new Answer();
        newAnswer.setContent(source.getContent());
        newAnswer.setCorrect(source.getCorrect());
        return newAnswer;
    }
    
}