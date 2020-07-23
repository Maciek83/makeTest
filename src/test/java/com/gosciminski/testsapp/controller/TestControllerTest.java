package com.gosciminski.testsapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gosciminski.testsapp.dto.display.TestQaDisplayDto;
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

    TestQaDisplayDto testQaDisplayDto = new TestQaDisplayDto();

    @BeforeEach
    public void setup() {

        JacksonTester.initFields(this, new ObjectMapper());

        mockMvc = MockMvcBuilders.standaloneSetup(testController).build();

        testQaDisplayDto.setId(1L);
        testQaDisplayDto.setName("name");
    }

    @Test
    public void findById() throws Exception {
        given(testQaServiceMock.findTestDisplayDtoById(any())).willReturn(testQaDisplayDto);

        MockHttpServletResponse response = mockMvc.perform(get("/api/test/" + testQaDisplayDto.getId())).andReturn()
                .getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), jsonTestQaDisplayDto.write(testQaDisplayDto).getJson());
    }
}