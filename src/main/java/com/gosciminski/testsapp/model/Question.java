package com.gosciminski.testsapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gosciminski.testsapp.buildiers.QuestionBuildier;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "question")
public class Question extends BaseEntity {
    
    @Column(name = "content")
    @NonNull
    private String content;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name="question_answer",
        joinColumns = @JoinColumn(name = "question_id"),
        inverseJoinColumns = @JoinColumn(name = "testqa_id")
    )
    private Set<TestQa> tests = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "question")
    private Set<Answer> answers = new HashSet<>();

    public Question() {
    }

    public Question(QuestionBuildier questionBuildier) {
        this.content = questionBuildier.getName();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<TestQa> getTests() {
        return tests;
    }

    public void setTests(Set<TestQa> tests) {
        this.tests = tests;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
}