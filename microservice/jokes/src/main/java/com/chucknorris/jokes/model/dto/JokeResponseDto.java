package com.chucknorris.jokes.model.dto;

public class JokeResponseDto {
    private String id;
    private String text;

    public JokeResponseDto() {
    }

    public JokeResponseDto(JokeDto joke) {
        this.id = joke.getId();
        this.text = joke.getValue();
    }

    @Override
    public String toString() {
        return "JokeDto [id=" + id + ", text=" + text + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
