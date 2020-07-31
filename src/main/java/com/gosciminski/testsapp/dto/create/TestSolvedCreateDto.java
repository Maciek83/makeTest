package com.gosciminski.testsapp.dto.create;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestSolvedCreateDto {

    @NotNull
    private Long id;

    @NotNull
    private Long testShareId;

    @NotEmpty
    private String name;

    @NotBlank
	@Email
    private String email;

    private Set<QuestionSolvedCreateDto> questions = new HashSet<>();
}