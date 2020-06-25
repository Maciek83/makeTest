package com.gosciminski.testsapp.converter;

import java.util.HashSet;
import java.util.Set;

import com.gosciminski.testsapp.dto.AnswerDisplayDto;
import com.gosciminski.testsapp.dto.QuestionDisplayDto;
import com.gosciminski.testsapp.model.Question;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class QuestionToQuestionDisplayDto implements Converter<Question, QuestionDisplayDto> {

    private final AnswerToAnswerDisplayDto answerToAnswerDisplayDto;

    public QuestionToQuestionDisplayDto(AnswerToAnswerDisplayDto answerToAnswerDisplayDto) {
        this.answerToAnswerDisplayDto = answerToAnswerDisplayDto;
    }

    @Override
    public QuestionDisplayDto convert(Question source) {
        QuestionDisplayDto dto = new QuestionDisplayDto();
        dto.setContent(source.getContent());

        Set<AnswerDisplayDto> answerDisplayDto = new HashSet<>();
        source.getAnswers().forEach(a-> answerDisplayDto.add(answerToAnswerDisplayDto.convert(a)));
        dto.setAnswers(answerDisplayDto);

        Set<Long> testsIds = new HashSet<>();
        source.getTests().forEach(t->testsIds.add(t.getId()));
        dto.setTestsIds(testsIds);

        return dto;
    }

    
}