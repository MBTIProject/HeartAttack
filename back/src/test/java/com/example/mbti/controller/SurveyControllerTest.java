package com.example.mbti.controller;

import com.example.mbti.dto.request.CommentRequestDto;
import com.example.mbti.dto.request.SurveyRequestDto;
import com.example.mbti.model.Comment;
import com.example.mbti.model.Poster;
import com.example.mbti.model.Survey;
import com.example.mbti.repository.Poster.PosterRepository;
import com.example.mbti.repository.survey.SurveyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SurveyControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PosterRepository posterRepository;

    @Autowired
    private SurveyRepository surveyRepository;

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

        Poster poster = posterRepository.findById(1L).get();
        List<Survey> surveyList = new ArrayList<>();
        for(int i=0; i<5; i++){
            Survey survey = Survey.builder()
                    .choice("선택지"+i)
                    .choiceResult("결과"+i)
                    .poster(poster)
                    .build();
            surveyList.add(survey);
        }surveyRepository.saveAll(surveyList);
    }

    @Test
    void 심리테스트_지문_등록_등록() throws JsonProcessingException {
        //given
        Long posterId = 1L;

        SurveyRequestDto surveyRequestDto = new SurveyRequestDto();
        surveyRequestDto.setChoice("선택지1");
        surveyRequestDto.setChoiceResult("결과1");

        String body = objectMapper.writeValueAsString(surveyRequestDto);

        //when
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange("/survey/" + posterId, HttpMethod.POST, request, String.class);

        //then
        List<Survey> posterList = surveyRepository.findByPosterPosterId(posterId);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(posterList.get(1).getChoice()).isEqualTo(surveyRequestDto.getChoice());
        assertThat(posterList.get(1).getChoiceResult()).isEqualTo(surveyRequestDto.getChoiceResult());
    }

    @Test
    void 심리테스트_결과_조회수(){
        //given
        Long posterId = 1L;
        Long surveyId = 1L;

        //when
        HttpEntity<String> request = new HttpEntity<>( headers);
        ResponseEntity<String> response = restTemplate.exchange("/survey/" +surveyId, HttpMethod.PUT, request, String.class);

        //then
        Survey survey = surveyRepository.findByPosterPosterId(posterId).stream()
                .filter(s -> s.getSurveyId().equals(surveyId))
                .findFirst().get();

        assertThat(survey.getChoiceViewCount()).isEqualTo(1);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void 심리테스트_선택지_결과_조회(){
        //given
        Long posterId = 1L;

        //then
        HttpEntity<String> request = new HttpEntity<>( headers);
        ResponseEntity<String> response = restTemplate.exchange("/survey/" + posterId, HttpMethod.GET, request, String.class);

        //when
        DocumentContext dc = JsonPath.parse(response.getBody());
        String choice = dc.read("$.data.data[0].choice");
        String choiceResult = dc.read("$.data.data[1].choiceResult");
        Integer choiceViewCount = dc.read("$.data.data[2].choiceViewCount");

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(choice).isEqualTo("선택지0");
        assertThat(choiceResult).isEqualTo("결과1");
        assertThat(choiceViewCount).isEqualTo(0);
    }

}