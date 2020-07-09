package com.gosciminski.testsapp.dto.edit;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TestQaEditDto {
    @NotNull
    @NotEmpty
    private String name;
    private Set<Long> questionsIds = new HashSet<>();
	public TestQaEditDto() {
	}

    public TestQaEditDto(@NotNull @NotEmpty String name, Set<Long> questionsIds) {
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