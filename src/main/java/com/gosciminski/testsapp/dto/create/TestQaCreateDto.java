package com.gosciminski.testsapp.dto.create;

import java.util.HashSet;
import java.util.Set;

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
public class TestQaCreateDto {
    
    @NotNull
    @NotEmpty
    private String name;
    
    private Set<Long> questionsIds = new HashSet<>();
}