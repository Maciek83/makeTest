package com.gosciminski.testsapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {

    private static final long serialVersionUID = -3060299873770067757L;

    @Column(unique = true)
    private String email;
    
    private String password;
    
    @Column(unique = true)
    private String name;
    private Integer active;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name="user_role",
	joinColumns = @JoinColumn(name="user_id"), 
	inverseJoinColumns = @JoinColumn(name="role_id"))
    @JsonManagedReference
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private Set<TestQa> tests = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private Set<TestQaShared> testsShared = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private Set<TestQaSolved> testsSolved = new HashSet<>();

    public User(String email, String password, String name, Integer active, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.active = active;
        this.roles = roles;
    }

    
}