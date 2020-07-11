package com.gosciminski.testsapp.dto.edit;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gosciminski.testsapp.dto.create.AnswerCreateDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEditDto {

    @NotNull
    @NotEmpty
    private String content;
    Set<AnswerEditDto> answerEditDto = new HashSet<>();
    Set<AnswerCreateDto> answerCreateDto = new HashSet<>();

}