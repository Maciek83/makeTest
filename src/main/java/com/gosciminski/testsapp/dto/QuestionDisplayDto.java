package com.gosciminski.testsapp.dto;

import java.util.HashSet;
import java.util.Set;

public class QuestionDisplayDto {
    private String content;
    private Set<Long> testsIds = new HashSet<>();
    private Set<AnswerDisplayDto> answers = new HashSet<>();

    public QuestionDisplayDto() {
    }

    public QuestionDisplayDto(String content, Set<Long> testsIds, Set<AnswerDisplayDto> answers) {
        this.content = content;
        this.testsIds = testsIds;
        this.answers = answers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Long> getTestsIds() {
        return testsIds;
    }

    public void setTestsIds(Set<Long> testsIds) {
        this.testsIds = testsIds;
    }

    public Set<AnswerDisplayDto> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerDisplayDto> answers) {
        this.answers = answers;
    }
    
}