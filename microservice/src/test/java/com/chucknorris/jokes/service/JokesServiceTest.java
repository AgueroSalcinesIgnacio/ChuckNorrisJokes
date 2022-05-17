package com.chucknorris.jokes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import com.chucknorris.jokes.model.dto.JokeDto;
import com.chucknorris.jokes.model.dto.JokeRequestDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JokesServiceTest {

	private static final String MOCK_VALUE = "mockValue";

	private static final String MOCK_ID = "mockId";

	@MockBean
	private RestTemplate restTemplateMock;

	@Autowired
	private JokeService jokeService;

	
	@Test
	void test() {

		JokeDto dtoMock = new JokeDto();
		dtoMock.setId(MOCK_ID);
		dtoMock.setValue(MOCK_VALUE);

		Mockito.when(restTemplateMock.getForObject(anyString(), any())).thenReturn(dtoMock);

		JokeDto response = jokeService.getJoke(new JokeRequestDto());

		assertNotNull(response);
		assertEquals(dtoMock.getId(), response.getId());
		assertEquals(dtoMock.getValue(), response.getValue());
	}

}
