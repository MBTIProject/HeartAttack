package com.example.mbti.controller;

import com.example.mbti.dto.request.CommentRequestDto;
import com.example.mbti.model.Comment;
import com.example.mbti.model.Poster;
import com.example.mbti.repository.CommentRepository;
import com.example.mbti.repository.Poster.PosterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
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
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PosterRepository posterRepository;

    @Autowired
    private CommentRepository commentRepository;

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

        List<Comment> commentList = new ArrayList<>();
        for(int i = 0; i<5; i++){
            Comment comment = Comment.builder()
                    .comment("댓글내용"+i)
                    .poster(posterRepository.findAllPosterAndSurvey().stream().findFirst().get())
                    .build();
            commentList.add(comment);
        }commentRepository.saveAll(commentList);
    }

    @Test
    void 심리테스트_유형별_댓글_등록() throws JsonProcessingException {
        //given
        Long posterId = 1L;

        CommentRequestDto commentRequestDto = new CommentRequestDto();
        commentRequestDto.setComment("댓글1");

        String body = objectMapper.writeValueAsString(commentRequestDto);

        //when
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange("/"+posterId +"/comments", HttpMethod.POST, request, String.class);

        //then
        List<Comment> commentList = commentRepository.findByPosterId(posterId);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(commentList.size()).isEqualTo(1);
        assertThat(commentList.get(0).getComment()).isEqualTo(commentRequestDto.getComment());
    }

    @Test
    void 심리테스트_유형별_댓글_조회(){
        //given
        Long posterId = 1L;

        //when
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/"+posterId +"/comments", HttpMethod.GET, request, String.class);

        System.out.println("response = " + response);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());
        Integer Id = dc.read("$.data.data[0].posterId");
        String comment = dc.read("$.data.data[1].comment");
        String msg = dc.read("$.msg");

        assertThat(Id).isEqualTo(Integer.valueOf(Math.toIntExact(posterId)));
        assertThat(msg).isEqualTo("댓글 조회 성공!");
        assertThat(comment).isEqualTo("댓글1");
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
}