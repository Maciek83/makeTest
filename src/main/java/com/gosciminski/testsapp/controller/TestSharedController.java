package com.gosciminski.testsapp.controller;

import java.util.List;

import com.gosciminski.testsapp.dto.create.ShareTestDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayToSolveDto;
import com.gosciminski.testsapp.dto.display.TestQaSharedDisplayDto;
import com.gosciminski.testsapp.service.TestQaShareService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class TestSharedController {

    private final TestQaShareService testQaShareService;

    public TestSharedController(TestQaShareService testQaShareService) {
		this.testQaShareService = testQaShareService;
    }
    
    @GetMapping(value = "/testshare")
    public ResponseEntity<List<TestQaSharedDisplayDto>> findAll(){
      String appUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
      return new ResponseEntity<>(testQaShareService.findAllByUser(appUrl), HttpStatus.OK);
    }
    
    @PostMapping(value = "/testshare")
    public ResponseEntity<TestQaDisplayToSolveDto> createTest(@RequestBody @Validated ShareTestDto shareTestDto) {
        Long id = Long.parseLong(shareTestDto.getId());
        return new ResponseEntity<>(testQaShareService.save(id), HttpStatus.CREATED);
    }
}