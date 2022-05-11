package com.chucknorris.jokes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.chucknorris.jokes.model.dto.JokeRequestDto;
import com.chucknorris.jokes.model.dto.JokeResponseDto;
import com.chucknorris.jokes.service.JokeService;

@RestController
@RequestMapping("/v1")
public class JokeController {

    private static final Logger log = LoggerFactory.getLogger(JokeController.class);

    @Autowired
    private JokeService jokeService;

    @PostMapping("/joke-request")
    public ResponseEntity<JokeResponseDto> getJoke(@RequestBody(required = false) JokeRequestDto joke) {
        log.info("called getJoke of JokeController");
        return new ResponseEntity<>(new JokeResponseDto(jokeService.getJoke(joke)),HttpStatus.OK);
    }
}