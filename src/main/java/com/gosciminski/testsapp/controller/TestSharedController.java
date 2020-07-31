package com.gosciminski.testsapp.controller;

import java.util.List;

import com.gosciminski.testsapp.dto.create.ShareTestCreateDto;
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
import org.springframework.web.bind.annotation.RequestParam;
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
  public ResponseEntity<List<TestQaSharedDisplayDto>> findAll() {
    String appUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    return new ResponseEntity<>(testQaShareService.findAllByUser(appUrl), HttpStatus.OK);
  }

  @GetMapping(value = "/testsharesolve")
  public ResponseEntity<TestQaDisplayToSolveDto> findTestToSolve(@RequestParam(name="id",required = true) Long id,
      @RequestParam(name="secret",required = true) String secret) {
    return new ResponseEntity<>(testQaShareService.findTestToSolve(id, secret), HttpStatus.OK);
  }

  @PostMapping(value = "/testshare")
  public ResponseEntity<TestQaDisplayToSolveDto> createTest(
      @RequestBody @Validated ShareTestCreateDto shareTestCreateDto) {
    return new ResponseEntity<>(testQaShareService.save(shareTestCreateDto), HttpStatus.CREATED);
  }
}