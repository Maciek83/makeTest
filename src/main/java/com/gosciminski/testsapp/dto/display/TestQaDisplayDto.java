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
public class TestQaDisplayDto {

    private Long id;
    private String name;
    private Set<QuestionDisplayDto> questionDisplayDto = new HashSet<>();
}