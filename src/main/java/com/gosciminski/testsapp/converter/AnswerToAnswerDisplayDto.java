package com.gosciminski.testsapp.converter;

import com.gosciminski.testsapp.dto.AnswerDisplayDto;
import com.gosciminski.testsapp.model.Answer;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AnswerToAnswerDisplayDto implements Converter<Answer, AnswerDisplayDto> {

    @Override
    public AnswerDisplayDto convert(Answer source) {
        AnswerDisplayDto dto = new AnswerDisplayDto();
        dto.setContent(source.getContent());
        dto.setId(source.getId());
        return dto;
    }
    
}