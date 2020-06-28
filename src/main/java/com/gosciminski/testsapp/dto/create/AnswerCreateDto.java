package com.gosciminski.testsapp.dto.create;

import org.springframework.lang.NonNull;

public class AnswerCreateDto {

    @NonNull
    private Boolean correct;
    @NonNull
    private String content;

    public AnswerCreateDto() {
    }

    public AnswerCreateDto(@NonNull Boolean correct, @NonNull String content) {
        this.correct = correct;
        this.content = content;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}