package com.gosciminski.testsapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
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
@Table(name="testqasolved")
public class TestQaSolved extends BaseEntity{

    private static final long serialVersionUID = -3984049117337851462L;

    @Column(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private TestQa test;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "testQaSolved")
    private Set<QuestionSolved> questioSolved = new HashSet<>();
}