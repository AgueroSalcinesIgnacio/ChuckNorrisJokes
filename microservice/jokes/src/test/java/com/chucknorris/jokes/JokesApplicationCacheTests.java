package com.chucknorris.jokes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.chucknorris.jokes.controller.JokeController;
import com.chucknorris.jokes.model.dto.JokeDto;
import com.chucknorris.jokes.model.dto.JokeRequestDto;
import com.chucknorris.jokes.model.dto.JokeResponseDto;
import com.chucknorris.jokes.service.JokeService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JokesApplicationCacheTests {

	private static final Logger log = LoggerFactory.getLogger(JokeService.class);

	private static final String MOCK_VALUE = "mockValue";

	private static final String MOCK_ID = "mockId";

	@Value("${api.chucknorris.test.path}")
	private String resourceUrl;

	@Value("${api.chucknorris.path}")
	private String url;

	@MockBean
	private RestTemplate restTemplateMock;

	@Autowired
	private JokeController jokeController;

	@BeforeTestClass
	public void setup() {
		log.info("Set up tests");
	}

	@AfterTestClass
	public void tearDown() {
		log.info("Tear down test");
	}

	@Test
	void getJokeCacheTest() {
		JokeDto dtoMock = new JokeDto(MOCK_ID, MOCK_VALUE);

		Mockito.when(restTemplateMock.getForObject(url, JokeDto.class)).thenReturn(dtoMock);

		JokeRequestDto jokeRequestDto = new JokeRequestDto();
		ResponseEntity<JokeResponseDto> responseCacheMiss = jokeController.getJoke(jokeRequestDto);
		ResponseEntity<JokeResponseDto> responseCache = jokeController.getJoke(jokeRequestDto);

		assertEquals(responseCacheMiss.getBody().getId(), dtoMock.getId());
		assertEquals(responseCacheMiss.getBody().getText(), dtoMock.getValue());

		assertEquals(responseCache.getBody().getId(), dtoMock.getId());
		assertEquals(responseCache.getBody().getText(), dtoMock.getValue());

		Mockito.verify(restTemplateMock, times(1)).getForObject(url, JokeDto.class);
	}

}
