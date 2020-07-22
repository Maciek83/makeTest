package com.gosciminski.testsapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gosciminski.testsapp.dto.create.AnswerCreateDto;
import com.gosciminski.testsapp.dto.create.QuestionCreateDto;
import com.gosciminski.testsapp.dto.display.QuestionDisplayDto;
import com.gosciminski.testsapp.dto.edit.QuestionEditDto;
import com.gosciminski.testsapp.service.QuestionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class QuestionControllerTest {

    @Mock
    QuestionService questionServiceMock;

    @InjectMocks
    QuestionController questionController;

    MockMvc mockMvc;

    private JacksonTester<QuestionDisplayDto> jsonQuestionDisplayDto;
    private JacksonTester<List<QuestionDisplayDto>> jsonQuestionDisplayDtoList;
    private JacksonTester<QuestionCreateDto> jsonQuestionCreateDto;
    private JacksonTester<QuestionEditDto> jsonQuestionEditDto;

    QuestionCreateDto validQuestion = new QuestionCreateDto();

    QuestionDisplayDto validDisplayQuestion = new QuestionDisplayDto();

    QuestionEditDto questionEditDto = new QuestionEditDto();

    List<QuestionDisplayDto> questions = new LinkedList<>();

    @BeforeEach
    public void setup() {

        JacksonTester.initFields(this, new ObjectMapper());

        validQuestion.setContent("question");
        AnswerCreateDto answer1 = new AnswerCreateDto();
        answer1.setContent("answer1");
        answer1.setCorrect(true);
        AnswerCreateDto answer2 = new AnswerCreateDto();
        answer2.setContent("answer2");
        answer2.setCorrect(false);
        validQuestion.getAnswers().add(answer1);
        validQuestion.getAnswers().add(answer2);

        validDisplayQuestion.setId(1L);
        validDisplayQuestion.setContent("question");

        questionEditDto.setContent("ssss");

        questions.add(validDisplayQuestion);

        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
    }

    @Test
    public void save() throws Exception {

        given(questionServiceMock.save(any())).willReturn(validDisplayQuestion);

        MockHttpServletResponse response = mockMvc.perform(post("/api/question/")
                .contentType(MediaType.APPLICATION_JSON).content(jsonQuestionCreateDto.write(validQuestion).getJson()))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.CREATED.value());
        assertEquals(response.getContentAsString(), jsonQuestionDisplayDto.write(validDisplayQuestion).getJson());
    }

    @Test
    public void findById() throws Exception {
        given(questionServiceMock.findQuestionDisplayDtoByid(1L)).willReturn(validDisplayQuestion);

        MockHttpServletResponse response = mockMvc.perform(get("/api/question/" + validDisplayQuestion.getId()))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), jsonQuestionDisplayDto.write(validDisplayQuestion).getJson());
    }

    @Test
    public void update() throws Exception{
        given(questionServiceMock.update(any(), any())).willReturn(validDisplayQuestion);

        MockHttpServletResponse response = mockMvc.perform(patch("/api/question/" + validDisplayQuestion.getId())
        .contentType(MediaType.APPLICATION_JSON).content(jsonQuestionEditDto.write(questionEditDto).getJson()))
        .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), jsonQuestionDisplayDto.write(validDisplayQuestion).getJson());
    }

    @Test
    public void findAll() throws Exception {
        given(questionServiceMock.findAllByUser()).willReturn(questions);

        MockHttpServletResponse response = mockMvc.perform(get("/api/question/"))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), jsonQuestionDisplayDtoList.write(questions).getJson());
    }

}