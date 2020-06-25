package com.gosciminski.testsapp.service.jpaimpl;

import java.util.HashSet;
import java.util.Set;

import com.gosciminski.testsapp.converter.TestQaToTestQaDisplayDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayDto;
import com.gosciminski.testsapp.model.TestQa;
import com.gosciminski.testsapp.repisitory.TestQaRepository;
import com.gosciminski.testsapp.service.TestQaService;

import org.springframework.stereotype.Service;

@Service
public class TestQaServiceJpa implements TestQaService {

    private final TestQaRepository repository;
    private final TestQaToTestQaDisplayDto converter;

    public TestQaServiceJpa(TestQaRepository repository, TestQaToTestQaDisplayDto converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Set<TestQaDisplayDto> findAll() {
        Iterable<TestQa> tests = repository.findAll();
        Set<TestQaDisplayDto> testsDto = new HashSet<>();
        tests.forEach(t-> testsDto.add(converter.convert(t)));
        return testsDto;
    }

    
}