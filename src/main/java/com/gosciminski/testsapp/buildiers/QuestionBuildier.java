package com.gosciminski.testsapp.buildiers;

import com.gosciminski.testsapp.model.Question;


public class QuestionBuildier {

    private String name;

    public QuestionBuildier() {
    }

    public QuestionBuildier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public QuestionBuildier setName(String name) {
        this.name = name;
        return this;
    }

    public Question build(){
        return new Question(this);
    }

}