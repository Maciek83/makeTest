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
public class AnswerCreateDto {

    @NotNull
    private Boolean correct;
    
    @NotNull
    @NotEmpty
    private String content;

}