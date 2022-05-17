package com.chucknorris.jokes.service;

import com.chucknorris.jokes.model.dto.JokeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JokeService {

    private static final Logger log = LoggerFactory.getLogger(JokeService.class);

    @Value("${api.chucknorris.path}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

  
    public JokeDto getJoke() {
        log.info("calling chuck norris jokes API");
        return restTemplate
                .getForObject(url, JokeDto.class);
    }

}
