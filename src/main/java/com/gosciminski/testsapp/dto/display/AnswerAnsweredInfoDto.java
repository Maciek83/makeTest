package com.gosciminski.testsapp.dto.display;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerAnsweredInfoDto {
    private String content;
    private Boolean correct;
    private Boolean userAnswer;
}