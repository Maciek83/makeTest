package com.gosciminski.testsapp.converter;

import com.gosciminski.testsapp.dto.display.AnswerAnsweredInfoDto;
import com.gosciminski.testsapp.dto.display.QuestionSolvedInfoDto;
import com.gosciminski.testsapp.dto.display.TestSolvedInfoDto;
import com.gosciminski.testsapp.model.TestQaSolved;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TestQaSolvedToTestSolvedInfoDto implements Converter<TestQaSolved, TestSolvedInfoDto>{

	@Override
	public TestSolvedInfoDto convert(TestQaSolved source) {
        TestSolvedInfoDto result = new TestSolvedInfoDto();

        result.setName(source.getTestQa().getName());
        result.setUserName(source.getName());

        source.getQuestioSolved().forEach(qs -> 
        {
            QuestionSolvedInfoDto qsDto = new QuestionSolvedInfoDto();
            qsDto.setContent(qs.getQuestion().getContent());
            result.getQuestionSolved().add(qsDto);

            qs.getAnswerAnswered().forEach(aa -> {
                
                AnswerAnsweredInfoDto answerAnsweredDto = new AnswerAnsweredInfoDto();

                if(aa.getAnswer().getCorrect() != aa.getCorrect())
                {
                    answerAnsweredDto.setCorrect(false);
                    answerAnsweredDto.setContent(aa.getAnswer().getContent());
                }
                else
                {
                    answerAnsweredDto.setCorrect(true);
                    answerAnsweredDto.setContent(aa.getAnswer().getContent());
                }

                qsDto.getAnswerAnswered().add(answerAnsweredDto);
            });

            if(qsDto.getAnswerAnswered().stream().filter(a -> a.getCorrect() == false).findAny().isPresent())
            {
                qsDto.setCorrect(false);
            }
            else
            {
                qsDto.setCorrect(true);
                result.addPoint();
            }

        });
        return result;
    }
}