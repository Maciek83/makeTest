package com.gosciminski.testsapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

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
    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private String name;
    private Integer active;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name="user_role", 
	joinColumns = @JoinColumn(name="user_id"), 
	inverseJoinColumns = @JoinColumn(name="role_id"))
	@JsonManagedReference
	private Set<Role> roles = new HashSet<>();
}