package com.gosciminski.testsapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
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
@Table(name = "testqashared")
public class TestQaShared extends BaseEntity{

    private static final long serialVersionUID = -4156308565512489886L;

    @ManyToOne(fetch = FetchType.EAGER)
    private TestQa test;

    @Column(name = "secret")
    @NotNull
    @NotEmpty
    private String secret;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}