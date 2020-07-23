package com.gosciminski.testsapp.dto.display;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDisplayToSolveDto {
    private Long id;
    private String content;
    private Set<AnswerDisplayToSolveDto> answers = new HashSet<>();
}