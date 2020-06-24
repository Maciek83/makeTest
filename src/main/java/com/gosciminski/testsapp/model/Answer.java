package com.gosciminski.testsapp.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="answer")
public class Answer extends BaseEntity {
    @Column(name = "content")
    @NonNull
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    public Answer() {
    }

    public Answer(String content, Question question) {
        this.content = content;
        this.question = question;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

}