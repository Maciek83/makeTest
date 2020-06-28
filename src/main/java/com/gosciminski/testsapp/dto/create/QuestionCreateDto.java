package com.gosciminski.testsapp.dto.create;

import java.util.HashSet;
import java.util.Set;

import org.springframework.lang.NonNull;

public class QuestionCreateDto {

    @NonNull
    private String content;
    Set<AnswerCreateDto> answers = new HashSet<>();


    public QuestionCreateDto() {
    }

    public QuestionCreateDto(@NonNull String content, Set<AnswerCreateDto> answers) {
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