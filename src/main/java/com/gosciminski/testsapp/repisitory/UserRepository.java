package com.gosciminski.testsapp.repisitory;

import com.gosciminski.testsapp.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
	User findByName(String name);
}