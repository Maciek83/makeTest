package com.gosciminski.testsapp.dto.create;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AnswerCreateDto {

    @NotNull
    @NotEmpty
    private Boolean correct;
    @NotNull
    @NotEmpty
    private String content;

    public AnswerCreateDto() {
    }

    public AnswerCreateDto(@NotNull @NotEmpty Boolean correct, @NotNull @NotEmpty String content) {
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