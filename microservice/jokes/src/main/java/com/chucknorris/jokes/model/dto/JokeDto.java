package com.chucknorris.jokes.model.dto;

public class JokeDto {
    private String id;
    private String value;

    public JokeDto() {
    }

    public JokeDto(String id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return "JokeDto [id=" + id + ", value=" + value + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
