package com.example.mbti.controller;

import com.example.mbti.dto.request.PosterRequestDto;
import com.example.mbti.dto.response.PosterResponseDto;
import com.example.mbti.model.Poster;
import com.example.mbti.repository.Poster.PosterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PosterControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PosterRepository posterRepository;

    private static ObjectMapper objectMapper;
    private static HttpHeaders headers;

    @BeforeAll
    public static void init(){
        objectMapper = new ObjectMapper();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @BeforeEach
    void initData(){
        List<Poster> posterList = new ArrayList<>();
        for(int i=0; i<5; i++){
            Poster poster = Poster.builder()
                    .posterTitle("심리테스트 유형 제목" + i)
                    .imgUrl("심리테스트 유형 이미지" + i)
                    .passage("심리테스트 유형 지문" + i)
                    .build();
            posterList.add(poster);
        }posterRepository.saveAll(posterList);
    }


    @Test
    @DisplayName("심리테스트_유형_추가")
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
        Integer statusCode = dc.read("$.code");

        assertThat(posterTitle).isEqualTo(posterRequestDto.getPosterTitle());
        assertThat(imgUrl).isEqualTo(posterRequestDto.getImgUrl());
        assertThat(passage).isEqualTo(posterRequestDto.getPassage());
        assertThat(statusCode).isEqualTo(202);
    }

    @Test
    @Sql("classpath:initdata/h2db/schema.sql")
    @DisplayName("심리테스트_유형_전체조회")
    void 심리테스트_유형_전체조회(){
        //given

        //when
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/posts", HttpMethod.GET, request, String.class);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());

        Integer posterId = dc.read("$.data.posterList[0].posterId");
        String posterTitle = dc.read("$.data.posterList[0].posterTitle");
        String imgUrl = dc.read("$.data.posterList[0].imgUrl");
        String passage = dc.read("$.data.posterList[0].passage");
        List<PosterResponseDto> posterResponseDtoList = dc.read("$.data.posterList");
        Integer statusCode = dc.read("$.code");

        assertThat(posterResponseDtoList.size()).isEqualTo(5);
        assertThat(posterId).isEqualTo(1);
        assertThat(posterTitle).isEqualTo("심리테스트 유형 제목0");
        assertThat(imgUrl).isEqualTo("심리테스트 유형 이미지0");
        assertThat(passage).isEqualTo("심리테스트 유형 지문0");
        assertThat(statusCode).isEqualTo(200);
    }

    @Test
    @DisplayName("심리테스트_조회수")
    void 심리테스트_조회수(){
        //given

        //when
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/posts/1", HttpMethod.PUT, request, String.class);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());
        Integer statusCode = dc.read("$.code");

        Poster poster = posterRepository.findById(1L).get();
        assertThat(poster.getPosterViewCount()).isEqualTo(1);
        assertThat(statusCode).isEqualTo(200);
    }
}