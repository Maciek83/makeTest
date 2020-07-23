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
public class TestQaDisplayToSolveDto {
    private Long id;
    private String name;
    private Set<QuestionDisplayToSolveDto> questionDisplayDto = new HashSet<>();
}