package com.chucknorris.jokes.service;

import com.chucknorris.jokes.model.dto.JokeDto;
import com.chucknorris.jokes.model.dto.JokeRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JokeService {

    private static final Logger log = LoggerFactory.getLogger(JokeService.class);

    @Value("${api.chucknorris.path}")
    private String url;

    public JokeDto getJoke(JokeRequestDto joke) {
        log.info("calling chuck norris jokes API");
        return new RestTemplate()
                .getForObject(url, JokeDto.class);
    }

}
