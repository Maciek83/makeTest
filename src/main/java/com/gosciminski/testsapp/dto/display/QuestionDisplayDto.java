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
public class QuestionDisplayDto {
    private Long id;
    private String content;
    private Set<Long> testsIds = new HashSet<>();
    private Set<AnswerDisplayDto> answers = new HashSet<>();
}