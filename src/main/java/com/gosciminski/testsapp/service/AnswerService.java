package com.gosciminski.testsapp.service;

import com.gosciminski.testsapp.model.Answer;

public interface AnswerService {
    Answer findById(Long id);
}