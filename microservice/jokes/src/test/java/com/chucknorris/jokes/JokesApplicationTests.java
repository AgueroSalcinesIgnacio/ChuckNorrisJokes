package com.chucknorris.jokes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.chucknorris.jokes.model.dto.JokeDto;
import com.chucknorris.jokes.model.dto.JokeRequestDto;
import com.chucknorris.jokes.model.dto.JokeResponseDto;
import com.chucknorris.jokes.service.JokeService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JokesApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(JokeService.class);

	private static final String MOCK_VALUE = "mockValue";

	private static final String MOCK_ID = "mockId";

	@MockBean
	private RestTemplate restTemplateMock;

	@Value("${api.chucknorris.path}")
	private String url;

	@Value("${api.chucknorris.test.path}")
	private String resourceUrl;

	@Autowired
	private JokeService jokeService;

	@BeforeTestClass
	public void setup() {
		log.info("Set up tests");
	}

	@AfterTestClass
	public void tearDown() {
		log.info("Tear down test");
	}

	@Test
	void getJokeUnitTest() {

		JokeDto dtoMock = new JokeDto();
		dtoMock.setId(MOCK_ID);
		dtoMock.setValue(MOCK_VALUE);

		Mockito.when(restTemplateMock.getForObject(url, JokeDto.class)).thenReturn(dtoMock);

		JokeDto response = jokeService.getJoke(new JokeRequestDto());

		assertNotNull(response);
		assertEquals(dtoMock.getId(), response.getId());
		assertEquals(dtoMock.getValue(), response.getValue());
	}

	@Test
	void getJokeIntegrationTest() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>("{}", headers);

		JokeResponseDto response = new RestTemplate().postForObject(resourceUrl, entity, JokeResponseDto.class);

		assertNotNull(response);
		assertTrue(!response.getId().isBlank());
		assertTrue(!response.getText().isBlank());
	}

}
