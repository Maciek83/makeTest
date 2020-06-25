package com.gosciminski.testsapp.converter;

import java.util.HashSet;
import java.util.Set;

import com.gosciminski.testsapp.dto.QuestionDisplayDto;
import com.gosciminski.testsapp.dto.TestQaDisplayDto;
import com.gosciminski.testsapp.model.TestQa;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TestQaToTestQaDisplayDto implements Converter<TestQa, TestQaDisplayDto>{

    private final QuestionToQuestionDisplayDto questiontoQuestionDisplayDto;

    public TestQaToTestQaDisplayDto(QuestionToQuestionDisplayDto questiontoQuestionDisplayDto) {
        this.questiontoQuestionDisplayDto = questiontoQuestionDisplayDto;
    }

	@Override
	public TestQaDisplayDto convert(TestQa source) {
        
        TestQaDisplayDto dto = new TestQaDisplayDto();
        dto.setName(source.getName());
        dto.setId(source.getId());

        Set<QuestionDisplayDto> questions = new HashSet<>();
        source.getQuestions().forEach(q-> questions.add(questiontoQuestionDisplayDto.convert(q)));
        dto.setQuestionDisplayDto(questions);

		return dto;
	}
    
}