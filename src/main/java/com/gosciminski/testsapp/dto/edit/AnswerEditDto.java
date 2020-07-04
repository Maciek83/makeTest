package com.gosciminski.testsapp.dto.edit;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AnswerEditDto {

    @NotNull
    @NotEmpty
    private Long id;
    @NotNull
    @NotEmpty
    private String content;
    @NotNull
    private Boolean correct;

    public AnswerEditDto() {
    }

    public AnswerEditDto(@NotNull @NotEmpty Long id, @NotNull @NotEmpty String content, @NotNull Boolean correct) {
        this.id = id;
        this.content = content;
        this.correct = correct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

}