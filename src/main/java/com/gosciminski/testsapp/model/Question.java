package com.gosciminski.testsapp.model;

import javax.persistence.Entity;

import com.gosciminski.testsapp.buildiers.QuestionBuildier;

@Entity
public class Question extends BaseEntity {
    
    private String name;

    public Question() {
    }

    public Question(QuestionBuildier questionBuildier) {
        this.name = questionBuildier.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}