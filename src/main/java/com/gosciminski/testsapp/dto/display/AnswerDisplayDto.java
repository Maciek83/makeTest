package com.gosciminski.testsapp.dto.display;

public class AnswerDisplayDto {
    private Long id;
    private String content;
    private Boolean correct;

    public AnswerDisplayDto() {
    }

    public AnswerDisplayDto(Long id, String content, Boolean correct) {
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