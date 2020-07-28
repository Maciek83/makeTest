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
@Table(name="answer")
public class Answer extends BaseEntity {
    
    private static final long serialVersionUID = -318979059202528181L;

    @Column(name = "content")
    @NotNull
    @NotEmpty
    private String content;

    @Column(name = "correct")
    @NotNull
    private Boolean correct;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "answer")
    private Set<AnsweredAnswer> answeredAnswers = new HashSet<>();

}