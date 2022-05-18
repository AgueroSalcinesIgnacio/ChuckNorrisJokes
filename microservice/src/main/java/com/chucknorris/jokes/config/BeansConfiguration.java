package com.chucknorris.jokes.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chucknorris.jokes.model.dto.JokeDto;
import com.chucknorris.jokes.model.dto.JokeResponseDto;

import org.modelmapper.ModelMapper;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeansConfiguration {

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<JokeDto, JokeResponseDto> myConverter = new Converter<JokeDto, JokeResponseDto>() {
            public JokeResponseDto convert(MappingContext<JokeDto, JokeResponseDto> context) {
                JokeDto dto = context.getSource();
                JokeResponseDto response = context.getDestination();
                response.setId(dto.getId());
                response.setText(dto.getValue());
                return response;
            }

        };

        modelMapper.addConverter(myConverter);
        return modelMapper;
    }
}
