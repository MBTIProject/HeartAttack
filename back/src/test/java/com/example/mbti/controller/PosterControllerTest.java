package com.example.mbti.controller;

import com.example.mbti.dto.request.PosterRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PosterControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static ObjectMapper objectMapper;
    private static HttpHeaders headers;

    @BeforeAll
    public static void init(){
        objectMapper = new ObjectMapper();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }


    @Test
    void 심리테스트_유형_추가() throws JsonProcessingException {
        //given
        PosterRequestDto posterRequestDto = new PosterRequestDto();
        posterRequestDto.setPosterTitle("심리테스트 유형제목1");
        posterRequestDto.setImgUrl("심리테스트 유형 이미지 주소1");
        posterRequestDto.setPassage("심리테스트 유형 지문1");

        String body = objectMapper.writeValueAsString(posterRequestDto);

        //when
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange("/posts", HttpMethod.POST, request, String.class);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());
        String posterTitle = dc.read("$.data.posterTitle");
        String imgUrl = dc.read("$.data.imgUrl");
        String passage = dc.read("$.data.passage");

        assertThat(posterTitle).isEqualTo(posterRequestDto.getPosterTitle());
        assertThat(imgUrl).isEqualTo(posterRequestDto.getImgUrl());
        assertThat(passage).isEqualTo(posterRequestDto.getPassage());
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
}