package com.gosciminski.testsapp.dto.create;

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
public class AnsweredAnswerCreateDto {

    @NotNull
    private Long id;
    
    @NotNull
    private Boolean correct;
}