package com.gosciminski.testsapp.converter;

import com.gosciminski.testsapp.dto.display.QuestionDisplayToSolveDto;
import com.gosciminski.testsapp.model.Question;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class QuestionToQuestionDisplayToSolveDto implements Converter<Question, QuestionDisplayToSolveDto> {

  private final AnswerToAnswerDisplayToSolveDto answerToAnswerDisplayToSolveDto;

  public QuestionToQuestionDisplayToSolveDto(AnswerToAnswerDisplayToSolveDto answerToAnswerDisplayToSolveDto) {
    this.answerToAnswerDisplayToSolveDto = answerToAnswerDisplayToSolveDto;
  }

  @Override
  public QuestionDisplayToSolveDto convert(Question source) {

    QuestionDisplayToSolveDto result = new QuestionDisplayToSolveDto();
    result.setId(source.getId());
    result.setContent(source.getContent());

    source.getAnswers().forEach(a -> result.getAnswers().add(answerToAnswerDisplayToSolveDto.convert(a)));

    return result;
  }

}