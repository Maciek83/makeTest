package com.gosciminski.testsapp.converter;

import com.gosciminski.testsapp.dto.display.AnswerDisplayToSolveDto;
import com.gosciminski.testsapp.model.Answer;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AnswerToAnswerDisplayToSolveDto implements Converter<Answer, AnswerDisplayToSolveDto> {

	@Override
	public AnswerDisplayToSolveDto convert(Answer source) {
        AnswerDisplayToSolveDto result = new AnswerDisplayToSolveDto();
        result.setContent(source.getContent());
		return result;
	}
    
}