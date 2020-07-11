package com.gosciminski.testsapp.dto.edit;

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
public class AnswerEditDto {

    @NotNull
    @NotEmpty
    private Long id;
    @NotNull
    @NotEmpty
    private String content;
    @NotNull
    private Boolean correct;

}