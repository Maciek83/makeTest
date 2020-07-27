package com.gosciminski.testsapp.dto.create;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatorDto {

    @NotBlank
	private String name;

	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String password;
}