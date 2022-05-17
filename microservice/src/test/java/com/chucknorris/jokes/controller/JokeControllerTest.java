package com.chucknorris.jokes.controller;

import com.chucknorris.jokes.model.dto.JokeDto;
import com.chucknorris.jokes.service.JokeService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class JokeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JokeService service;

    private static final String MOCK_VALUE = "mockValue";

    private static final String MOCK_ID = "mockId";

    @Test
    void test() throws Exception {
        JokeDto dtoMock = new JokeDto();
        dtoMock.setId(MOCK_ID);
        dtoMock.setValue(MOCK_VALUE);

        Mockito.when(service.getJoke(null)).thenReturn(dtoMock);

        mvc.perform(MockMvcRequestBuilders.post("/v1/joke-request"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }
}
