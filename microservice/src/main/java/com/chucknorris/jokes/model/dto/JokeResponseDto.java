package com.chucknorris.jokes.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class JokeResponseDto implements Serializable {
    private String id;
    private String text;
}
