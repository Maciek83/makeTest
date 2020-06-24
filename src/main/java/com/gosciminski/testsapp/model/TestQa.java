package com.gosciminski.testsapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "testqa")
public class TestQa extends BaseEntity{
    
    @Column(name = "name")
    @NonNull
    private String name;
    @ManyToMany(mappedBy = "tests", fetch = FetchType.EAGER)
    private Set<Question> questions = new HashSet<>();

    public TestQa() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}