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
public class TestSolvedInfoDto {
    private String name;
    private String userName;
    private List<QuestionSolvedInfoDto> questionSolved = new LinkedList<>();
}