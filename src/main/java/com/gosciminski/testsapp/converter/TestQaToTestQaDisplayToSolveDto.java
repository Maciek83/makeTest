package com.gosciminski.testsapp.converter;

import com.gosciminski.testsapp.dto.display.TestQaDisplayToSolveDto;
import com.gosciminski.testsapp.model.TestQa;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TestQaToTestQaDisplayToSolveDto implements Converter<TestQa, TestQaDisplayToSolveDto>{
    
    private final QuestionToQuestionDisplayToSolveDto questionToQuestionDisplayToSolveDto;

    public TestQaToTestQaDisplayToSolveDto(QuestionToQuestionDisplayToSolveDto questionToQuestionDisplayToSolveDto) {
        this.questionToQuestionDisplayToSolveDto = questionToQuestionDisplayToSolveDto;
    }

	@Override
	public TestQaDisplayToSolveDto convert(TestQa source) {
        
        TestQaDisplayToSolveDto result = new TestQaDisplayToSolveDto();
        result.setId(source.getId());
        result.setName(source.getName());

        source.getQuestions().forEach(q ->  result.getQuestionDisplayDto().add(questionToQuestionDisplayToSolveDto.convert(q)));
        
		return result;
	}

    
}