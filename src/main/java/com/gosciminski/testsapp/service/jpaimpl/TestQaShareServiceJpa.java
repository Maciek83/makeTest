package com.gosciminski.testsapp.service.jpaimpl;

import com.gosciminski.testsapp.converter.TestQaToTestQaDisplayToSolveDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayToSolveDto;
import com.gosciminski.testsapp.model.TestQa;
import com.gosciminski.testsapp.model.TestQaShared;
import com.gosciminski.testsapp.model.User;
import com.gosciminski.testsapp.repisitory.TestQaSharedRepository;
import com.gosciminski.testsapp.service.TestQaService;
import com.gosciminski.testsapp.service.TestQaShareService;
import com.gosciminski.testsapp.service.UserService;
import com.gosciminski.testsapp.utils.SecretGenerator;

import org.springframework.stereotype.Service;

@Service
public class TestQaShareServiceJpa implements TestQaShareService{
    private final TestQaSharedRepository testQaSharedRepository;
    private final TestQaService testService;
    private final SecretGenerator secretGenerator;
    private final TestQaToTestQaDisplayToSolveDto testQaToTestQaDisplayToSolveDto;
    private final UserService userService;

    public TestQaShareServiceJpa(TestQaSharedRepository testQaSharedRepository, TestQaService testService,
            SecretGenerator secretGenerator, TestQaToTestQaDisplayToSolveDto testQaToTestQaDisplayToSolveDto,
            UserService userService) {
        this.testQaSharedRepository = testQaSharedRepository;
        this.testService = testService;
        this.secretGenerator = secretGenerator;
        this.testQaToTestQaDisplayToSolveDto = testQaToTestQaDisplayToSolveDto;
        this.userService = userService;
    }

    @Override
    public TestQaDisplayToSolveDto save(Long id) {
        
        TestQa source = testService.findById(id);
        TestQaShared toSave = new TestQaShared();
        toSave.setTest(source);
        toSave.setSecret(secretGenerator.generateSecret());
        source.setTestQaShared(toSave);
        User loggedInUser = userService.getUser();
        loggedInUser.getTestsShared().add(toSave);
        toSave.setUser(loggedInUser);
        
        TestQaShared saved = testQaSharedRepository.save(toSave);
        
        return testQaToTestQaDisplayToSolveDto.convert(saved.getTest());
    }

    
}