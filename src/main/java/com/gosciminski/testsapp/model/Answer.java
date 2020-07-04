package com.gosciminski.testsapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="answer")
public class Answer extends BaseEntity {
    @Column(name = "content")
    @NotNull
    @NotEmpty
    private String content;
    @NotNull
    private Boolean correct;
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    public Answer() {
    }

    public Answer(@NotNull @NotEmpty String content, @NotNull Boolean correct, Question question) {
        this.content = content;
        this.correct = correct;
        this.question = question;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

}