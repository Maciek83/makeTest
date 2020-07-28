package com.gosciminski.testsapp.dto.display;

import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSolvedInfoDto {
    private String content;
    private Boolean correct;
    private List<AnswerAnsweredInfoDto> answerAnswered = new LinkedList<>();
}