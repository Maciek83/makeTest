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
import com.gosciminski.testsapp.dto.create.TestQaCreateDto;
import com.gosciminski.testsapp.dto.display.TestQaDisplayDto;
import com.gosciminski.testsapp.dto.edit.TestQaEditDto;
import com.gosciminski.testsapp.service.TestQaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class TestControllerTest {

    @Mock
    TestQaService testQaServiceMock;

    @InjectMocks
    TestController testController;

    MockMvc mockMvc;

    private JacksonTester<TestQaDisplayDto> jsonTestQaDisplayDto;
    private JacksonTester<List<TestQaDisplayDto>> jsonTestQaDisplayDtoList;
    private JacksonTester<TestQaCreateDto> jsonTestQaCreateDto;
    private JacksonTester<TestQaEditDto> jsonTestQaEditDto;

    TestQaDisplayDto testQaDisplayDto = new TestQaDisplayDto();
    List<TestQaDisplayDto> testList = new LinkedList<>();
    TestQaCreateDto testCreator = new TestQaCreateDto();
    TestQaEditDto testEditDto = new TestQaEditDto();

    @BeforeEach
    public void setup() {

        JacksonTester.initFields(this, new ObjectMapper());

        mockMvc = MockMvcBuilders.standaloneSetup(testController).build();

        testQaDisplayDto.setId(1L);
        testQaDisplayDto.setName("name");

        testList.add(testQaDisplayDto);

        testCreator.setName("test");
        testCreator.getQuestionsIds().add(1L);
        testCreator.getQuestionsIds().add(2L);

        testEditDto.setName("test");
        testEditDto.getQuestionsIds().add(1L);
        testEditDto.getQuestionsIds().add(2L);
    }

    @Test
    public void findAll_shouldFind() throws Exception {
        given(testQaServiceMock.findAllByUser()).willReturn(testList);

        MockHttpServletResponse response = mockMvc.perform(get("/api/test")).andReturn()
                .getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), jsonTestQaDisplayDtoList.write(testList).getJson());
    }

    @Test
    public void findById_shouldFind() throws Exception {
        given(testQaServiceMock.findTestDisplayDtoById(any())).willReturn(testQaDisplayDto);

        MockHttpServletResponse response = mockMvc.perform(get("/api/test/" + testQaDisplayDto.getId())).andReturn()
                .getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), jsonTestQaDisplayDto.write(testQaDisplayDto).getJson());
    }

    @Test
    public void createTest_shouldCreate() throws Exception {
        given(testQaServiceMock.save(any(TestQaCreateDto.class))).willReturn(testQaDisplayDto);

        MockHttpServletResponse response = mockMvc.perform(post("/api/test/")
                .contentType(MediaType.APPLICATION_JSON).content(jsonTestQaCreateDto.write(testCreator).getJson()))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.CREATED.value());
        assertEquals(response.getContentAsString(), jsonTestQaDisplayDto.write(testQaDisplayDto).getJson());
    }

    @Test
    public void editTest_shouldEdit() throws Exception {
        given(testQaServiceMock.update(any(), any())).willReturn(testQaDisplayDto);

        MockHttpServletResponse response = mockMvc.perform(patch("/api/test/" + testQaDisplayDto.getId())
        .contentType(MediaType.APPLICATION_JSON).content(jsonTestQaEditDto.write(testEditDto).getJson()))
        .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), jsonTestQaDisplayDto.write(testQaDisplayDto).getJson());
    }
}