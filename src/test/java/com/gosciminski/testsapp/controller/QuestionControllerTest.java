package com.gosciminski.testsapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gosciminski.testsapp.dto.create.AnswerCreateDto;
import com.gosciminski.testsapp.dto.create.QuestionCreateDto;
import com.gosciminski.testsapp.dto.display.QuestionDisplayDto;
import com.gosciminski.testsapp.service.QuestionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
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
    private JacksonTester<QuestionCreateDto> jsonQuestionCreateDto;

    QuestionCreateDto validQuestion = new QuestionCreateDto();

    QuestionDisplayDto validDisplayQuestion = new QuestionDisplayDto();

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

}