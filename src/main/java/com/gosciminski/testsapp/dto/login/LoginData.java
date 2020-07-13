package com.gosciminski.testsapp.dto.login;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginData {
    @NotBlank
	private String name;
	@NotBlank
	private String password;
}