package com.gosciminski.testsapp.dto.create;

import java.util.HashSet;
import java.util.Set;

public class TestQaCreateDto {
    private String name;
    private Set<Long> questionsIds = new HashSet<>();

    public TestQaCreateDto() {
    }

    public TestQaCreateDto(String name, Set<Long> questionsIds) {
        this.name = name;
        this.questionsIds = questionsIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getQuestionsIds() {
        return questionsIds;
    }

    public void setQuestionsIds(Set<Long> questionsIds) {
        this.questionsIds = questionsIds;
    }
    
}