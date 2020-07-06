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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "question")
public class Question extends BaseEntity {
    
    @Column(name = "content")
    @NotNull
    @NotEmpty
    private String content;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name="question_answer",
        joinColumns = @JoinColumn(name = "question_id"),
        inverseJoinColumns = @JoinColumn(name = "testqa_id")
    )
    private Set<TestQa> tests = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "question")
    private Set<Answer> answers = new HashSet<>();


    public Question() {
    }

    public Question(@NotNull @NotEmpty String content, Set<TestQa> tests, Set<Answer> answers) {
		this.content = content;
		this.tests = tests;
		this.answers = answers;
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