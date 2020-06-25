package com.gosciminski.testsapp.dto.create;

public class AnswerCreateDto {

    private Boolean correct;
    private String content;

    public AnswerCreateDto() {
    }

    public AnswerCreateDto(Boolean correct, String content) {
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