package com.gosciminski.testsapp.converter;

import java.util.HashSet;
import java.util.Set;

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
        
        Set<Answer> answers = new HashSet<>();
        source.getAnswers().forEach(a-> 
        {
            Answer answer = answerCreateDtoToAnswer.convert(a);
            answer.setQuestion(newQuestion);
            answers.add(answer);
        });
        newQuestion.setAnswers(answers);
        return newQuestion;
    }
    
    
}