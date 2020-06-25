package com.gosciminski.testsapp.dto.create;

import java.util.HashSet;
import java.util.Set;

public class TestQaCreateDto {
    private String name;
    private Set<QuestionCreateDto> questions = new HashSet<>();

    public TestQaCreateDto() {
    }

    public TestQaCreateDto(String name, Set<QuestionCreateDto> questions) {
        this.name = name;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<QuestionCreateDto> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionCreateDto> questions) {
        this.questions = questions;
    }
    
}