package com.gosciminski.testsapp.dto.create;

import java.util.HashSet;
import java.util.Set;

public class QuestionCreateDto {

    private String content;
    Set<AnswerCreateDto> goodAnswers = new HashSet<>();
    Set<AnswerCreateDto> badAnswers = new HashSet<>();

    public QuestionCreateDto() {
    }

    public QuestionCreateDto(String content, Set<AnswerCreateDto> goodAnswers, Set<AnswerCreateDto> badAnswers) {
        this.content = content;
        this.goodAnswers = goodAnswers;
        this.badAnswers = badAnswers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<AnswerCreateDto> getGoodAnswers() {
        return goodAnswers;
    }

    public void setGoodAnswers(Set<AnswerCreateDto> goodAnswers) {
        this.goodAnswers = goodAnswers;
    }

    public Set<AnswerCreateDto> getBadAnswers() {
        return badAnswers;
    }

    public void setBadAnswers(Set<AnswerCreateDto> badAnswers) {
        this.badAnswers = badAnswers;
    }
    
}