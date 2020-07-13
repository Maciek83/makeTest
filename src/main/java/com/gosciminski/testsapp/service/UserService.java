package com.gosciminski.testsapp.service;

import com.gosciminski.testsapp.dto.create.UserCreatorDto;
import com.gosciminski.testsapp.model.User;

public interface UserService {
    User saveUser(UserCreatorDto user);
	User findUserByName(String name);
}