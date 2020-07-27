package com.gosciminski.testsapp.converter;

import com.gosciminski.testsapp.dto.create.QuestionCreateDto;
import com.gosciminski.testsapp.model.Answer;
import com.gosciminski.testsapp.model.Question;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class QuestionCreateDtoToQuestion implements Converter<QuestionCreateDto,Question> {

    private final AnswerCreateDtoToAnswer answerCreateDtoToAnswer;

    public QuestionCreateDtoToQuestion(AnswerCreateDtoToAnswer answerCreateDtoToAnswer) {
        this.answerCreateDtoToAnswer = answerCreateDtoToAnswer;
    }

    @Override
    public Question convert(QuestionCreateDto source) {
        Question newQuestion = new Question();
        newQuestion.setContent(source.getContent());
        

        source.getAnswers().forEach(a-> 
        {
            Answer answer = answerCreateDtoToAnswer.convert(a);
            answer.setQuestion(newQuestion);
            newQuestion.getAnswers().add(answer);
        });

        return newQuestion;
    }
    
    
}