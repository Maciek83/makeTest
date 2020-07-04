package com.gosciminski.testsapp.dto.edit;

import java.util.HashSet;
import java.util.Set;

import com.gosciminski.testsapp.dto.create.AnswerCreateDto;

public class QuestionEditDto {

    private Long id;
    private String content;
    Set<AnswerEditDto> answerEditDto = new HashSet<>();
    Set<AnswerCreateDto> answerCreateDto = new HashSet<>();

    public QuestionEditDto() {
    }

    public QuestionEditDto(Long id, String content, Set<AnswerEditDto> answerEditDto,
            Set<AnswerCreateDto> answerCreateDto) {
        this.id = id;
        this.content = content;
        this.answerEditDto = answerEditDto;
        this.answerCreateDto = answerCreateDto;
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

    public Set<AnswerEditDto> getAnswerEditDto() {
        return answerEditDto;
    }

    public void setAnswerEditDto(Set<AnswerEditDto> answerEditDto) {
        this.answerEditDto = answerEditDto;
    }

    public Set<AnswerCreateDto> getAnswerCreateDto() {
        return answerCreateDto;
    }

    public void setAnswerCreateDto(Set<AnswerCreateDto> answerCreateDto) {
        this.answerCreateDto = answerCreateDto;
    }

}