package com.gosciminski.testsapp.dto.create;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class QuestionCreateDto {

    @NotNull
    @NotEmpty
    private String content;
    Set<AnswerCreateDto> answers = new HashSet<>();

    public QuestionCreateDto() {
    }

	public QuestionCreateDto(@NotNull @NotEmpty String content, Set<AnswerCreateDto> answers) {
		this.content = content;
		this.answers = answers;
	}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<AnswerCreateDto> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerCreateDto> answers) {
        this.answers = answers;
    }
    
}