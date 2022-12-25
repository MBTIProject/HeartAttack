package com.example.mbti.controller;

import com.example.mbti.dto.request.PosterRequestDto;
import com.example.mbti.model.Poster;
import com.example.mbti.repository.Poster.PosterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

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

    @Test
    void 심리테스트_유형_전체조회(){
        //given

        //when
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/posts", HttpMethod.GET, request, String.class);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());
        Object dataObject = dc.read("$.data");

        DocumentContext poster0 = JsonPath.parse(((List<?>) dataObject).get(0));
        DocumentContext poster1 = JsonPath.parse(((List<?>) dataObject).get(1));
        DocumentContext poster2 = JsonPath.parse(((List<?>) dataObject).get(2));
        DocumentContext poster3 = JsonPath.parse(((List<?>) dataObject).get(3));
        DocumentContext poster4 = JsonPath.parse(((List<?>) dataObject).get(4));

        Object posterId0 = poster0.read("$.posterId");
        Object posterTitle1 = poster1.read("$.posterTitle");
        Object imgUrl2 = poster2.read("$.imgUrl");
        Object posterViewCount3 = poster3.read("$.posterViewCount");
        Object passage4 = poster4.read("$.passage");

        int objectSize = ((List<?>) dataObject).size();

        assertThat(posterId0).isEqualTo(1);
        assertThat(posterTitle1).isEqualTo("심리테스트 유형 제목1");
        assertThat(imgUrl2).isEqualTo("심리테스트 유형 이미지2");
        assertThat(posterViewCount3).isEqualTo(0);
        assertThat(passage4).isEqualTo("심리테스트 유형 지문4");
        assertThat(objectSize).isEqualTo(5);
    }

    @Test
    void 심리테스트_조회수(){
        //given

        //when
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/posts/1", HttpMethod.PUT, request, String.class);

        //then
        Poster poster = posterRepository.findById(1L).get();
        assertThat(poster.getPosterViewCount()).isEqualTo(1);

    }
}