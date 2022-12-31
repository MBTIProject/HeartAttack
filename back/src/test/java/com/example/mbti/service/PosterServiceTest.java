package com.example.mbti.service;

import com.example.mbti.dto.request.PosterRequestDto;
import com.example.mbti.dto.response.PosterResponseDto;
import com.example.mbti.model.Poster;
import com.example.mbti.repository.Poster.PosterRepository;
import com.example.mbti.service.impl.PosterServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PosterServiceTest {

    @InjectMocks
    private PosterServiceImpl posterService;

    @Mock
    private PosterRepository posterRepository;

    private static ObjectMapper objectMapper;

    @Test
    @DisplayName("심리테스트_유형_추가")
    void 심리테스트_유형_추가() {
        //given
        PosterRequestDto dto = new PosterRequestDto();
        dto.setPosterTitle("심리테스트 유형3");
        dto.setImgUrl("심리테스트 유형 주소1");
        dto.setPassage("지문1");

        Poster poster = Poster.builder()
                .posterTitle(dto.getPosterTitle())
                .imgUrl(dto.getImgUrl())
                .passage(dto.getPassage())
                .build();

        //stub
        when(posterRepository.save(any())).thenReturn(poster);
        when(posterRepository.findByPosterTitle(dto.getPosterTitle())).thenReturn(Optional.ofNullable(null));

        //when
        PosterResponseDto posterResponseDto = posterService.addPost(dto);

        //then
        assertThat(posterResponseDto.getPosterTitle()).isEqualTo(dto.getPosterTitle());
        assertThat(posterResponseDto.getImgUrl()).isEqualTo(dto.getImgUrl());
        assertThat(posterResponseDto.getPassage()).isEqualTo(dto.getPassage());
    }

    @Test
    @DisplayName("심리테스트_유형_조회")
    void 심리테스트_유형_조회(){
        //given
        List<Poster> posterList = new ArrayList<>();
        for(int i=0; i<5; i++){
            Poster poster = Poster.builder()
                    .posterTitle("심리테스트 유형" + i)
                    .imgUrl("심리테스트 유형 이미지 주소" + i)
                    .passage("심리테스트 지문" + i)
                    .build();
            posterList.add(poster);
        }
        //stub
        when(posterRepository.findAllPosterAndSurvey()).thenReturn(posterList);

        //when
        PosterResponseDto.PosterList poster = posterService.findPost();

        //then
        assertThat(poster.getPosterList().get(0).getPosterTitle()).isEqualTo("심리테스트 유형0");
        assertThat(poster.getPosterList().get(1).getPassage()).isEqualTo("심리테스트 지문1");
        assertThat(poster.getPosterList().get(2).getImgUrl()).isEqualTo("심리테스트 유형 이미지 주소2");
        assertThat(poster.getPosterList().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("심리테스트_유형_조회수")
    void 심리테스트_유형_조회수(){
        //given
        Long posterId = 1L;
        Optional<Poster> poster = Optional.of(Poster.builder()
                .posterId(posterId)
                .posterTitle("심리테스트 유형1")
                .imgUrl("심리테스트 유형 이미지 주소1")
                .passage("심리테스트 지문1")
                .build());
        //stub
        when(posterRepository.findById(posterId)).thenReturn(poster);

        //when
        Poster modifyPoster = posterService.modifyPostCnt(posterId).get();

        //then
        assertThat(modifyPoster.getPosterViewCount()).isEqualTo(1);
    }
}