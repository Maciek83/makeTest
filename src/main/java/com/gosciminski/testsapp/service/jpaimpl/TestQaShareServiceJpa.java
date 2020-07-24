package com.gosciminski.testsapp.service.jpaimpl;

import java.util.LinkedList;
import java.util.List;

import com.gosciminski.testsapp.converter.TestQaSharedToTestQaSharedDisplayDto;
import com.gosciminski.testsapp.converter.TestQaToTestQaDisplayToSolveDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayToSolveDto;
import com.gosciminski.testsapp.dto.display.TestQaSharedDisplayDto;
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
public class TestQaShareServiceJpa implements TestQaShareService {
    private final TestQaSharedRepository testQaSharedRepository;
    private final TestQaService testService;
    private final SecretGenerator secretGenerator;
    private final TestQaToTestQaDisplayToSolveDto testQaToTestQaDisplayToSolveDto;
    private final UserService userService;
    private final TestQaSharedToTestQaSharedDisplayDto testQaSharedToTestQaSharedDisplayDto;

    public TestQaShareServiceJpa(TestQaSharedRepository testQaSharedRepository, TestQaService testService,
            SecretGenerator secretGenerator, TestQaToTestQaDisplayToSolveDto testQaToTestQaDisplayToSolveDto,
            UserService userService, TestQaSharedToTestQaSharedDisplayDto testQaSharedToTestQaSharedDisplayDto) {
        this.testQaSharedRepository = testQaSharedRepository;
        this.testService = testService;
        this.secretGenerator = secretGenerator;
        this.testQaToTestQaDisplayToSolveDto = testQaToTestQaDisplayToSolveDto;
        this.userService = userService;
        this.testQaSharedToTestQaSharedDisplayDto = testQaSharedToTestQaSharedDisplayDto;
    }

    @Override
    public TestQaDisplayToSolveDto save(Long id) {

        TestQa source = testService.findById(id);
        TestQaShared toSave = new TestQaShared();
        toSave.setTest(source);
        toSave.setSecret(secretGenerator.generateSecret());
        source.getTestsQaShared().add(toSave);
        User loggedInUser = userService.getUser();
        loggedInUser.getTestsShared().add(toSave);
        toSave.setUser(loggedInUser);

        TestQaShared saved = testQaSharedRepository.save(toSave);

        return testQaToTestQaDisplayToSolveDto.convert(saved.getTest());
    }

    @Override
    public List<TestQaSharedDisplayDto> findAllByUser(String appUrl) {
        Iterable<TestQaShared> tests = testQaSharedRepository.findByUser(userService.getUser());
        List<TestQaSharedDisplayDto> testsDto = new LinkedList<>();
        testQaSharedToTestQaSharedDisplayDto.setAppUrl(appUrl);
        tests.forEach(t -> testsDto.add(testQaSharedToTestQaSharedDisplayDto.convert(t)));
        return testsDto;
    }

    
}