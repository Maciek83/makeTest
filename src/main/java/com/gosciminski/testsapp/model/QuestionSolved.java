package com.gosciminski.testsapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="questionsolved")
public class QuestionSolved extends BaseEntity{

    private static final long serialVersionUID = -3611243387988109046L;

    @NotNull
    private Question question;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "quesionSolved")
    private Set<AnsweredAnswer> answerAnswered = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private TestQaSolved testQaSolved;

    @Column(name = "correct")
    @NotNull
    private Boolean correct;
}