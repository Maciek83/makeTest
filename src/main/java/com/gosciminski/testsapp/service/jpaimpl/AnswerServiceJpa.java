package com.gosciminski.testsapp.service.jpaimpl;

import com.gosciminski.testsapp.exceptions.AnswerNotFoundException;
import com.gosciminski.testsapp.model.Answer;
import com.gosciminski.testsapp.repisitory.AnswerRepository;
import com.gosciminski.testsapp.service.AnswerService;

import org.springframework.stereotype.Service;

@Service
public class AnswerServiceJpa implements AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerServiceJpa(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer findById(Long id) {
        return answerRepository.findById(id).orElseThrow(()-> new AnswerNotFoundException(id));
    }

    
}