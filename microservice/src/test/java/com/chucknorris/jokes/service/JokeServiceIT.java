package com.chucknorris.jokes.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.chucknorris.jokes.model.dto.JokeDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JokeServiceIT {
    
    @Autowired
    private JokeService service;

    @Test
    void test() {

        JokeDto response = service.getJoke();

        assertNotNull(response);
        assertTrue(!response.getId().isBlank());
        assertTrue(!response.getValue().isBlank());
    }

}
