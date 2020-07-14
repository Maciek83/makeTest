package com.gosciminski.testsapp.controller;

import java.security.Principal;

import com.gosciminski.testsapp.dto.create.UserCreatorDto;
import com.gosciminski.testsapp.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

    @CrossOrigin
	@GetMapping(value = "/login")
	public Principal user(Principal user) {
		return user;
    }

	@CrossOrigin
	@PostMapping(value = "/register")
	public void register(@RequestBody @Validated UserCreatorDto userCreator){
		userService.saveUser(userCreator);
	}

	@CrossOrigin
	@PostMapping(value = "/logout")
	public ResponseEntity<String> logout(){
		SecurityContextHolder.clearContext();
		return new ResponseEntity<String>("Logout Successfully!", HttpStatus.OK);
	}


}