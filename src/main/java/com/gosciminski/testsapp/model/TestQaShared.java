package com.gosciminski.testsapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
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
@Table(name = "testqashared")
public class TestQaShared extends BaseEntity{
    @OneToOne(mappedBy = "testQaShared")
    private TestQa test;
    @Column(name = "secret")
    @NotNull
    @NotEmpty
    private String secret;
}