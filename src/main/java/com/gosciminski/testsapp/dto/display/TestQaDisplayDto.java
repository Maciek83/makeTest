package com.gosciminski.testsapp.dto.display;

import java.util.HashSet;
import java.util.Set;

public class TestQaDisplayDto {

    private Long id;
    private String name;
    private Set<QuestionDisplayDto> questionDisplayDto = new HashSet<>();

    public TestQaDisplayDto() {
    }

    public TestQaDisplayDto(String name, Set<QuestionDisplayDto> questionDisplayDto) {
        this.name = name;
        this.questionDisplayDto = questionDisplayDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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