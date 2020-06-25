package com.gosciminski.testsapp.dto;

import java.util.HashSet;
import java.util.Set;

public class TestQaDisplayDto {

    private String name;
    private Set<QuestionDisplayDto> questionDisplayDto = new HashSet<>();

    public TestQaDisplayDto() {
    }

    public TestQaDisplayDto(String name, Set<QuestionDisplayDto> questionDisplayDto) {
        this.name = name;
        this.questionDisplayDto = questionDisplayDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<QuestionDisplayDto> getQuestionDisplayDto() {
        return questionDisplayDto;
    }

    public void setQuestionDisplayDto(Set<QuestionDisplayDto> questionDisplayDto) {
        this.questionDisplayDto = questionDisplayDto;
    }
    
}