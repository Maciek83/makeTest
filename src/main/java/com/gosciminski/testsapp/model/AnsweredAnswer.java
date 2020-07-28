package com.gosciminski.testsapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="answeredanswer")
public class AnsweredAnswer extends BaseEntity {
    
    private static final long serialVersionUID = -7246620979706145259L;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @NotNull
    private QuestionSolved quesionSolved;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Answer answer;

    @NotNull
    private Boolean correct;

}