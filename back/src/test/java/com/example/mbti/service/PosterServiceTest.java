package com.example.mbti.service;

import com.example.mbti.dto.request.PosterRequestDto;
import com.example.mbti.dto.response.PosterResponseDto;
import com.example.mbti.model.Poster;
import com.example.mbti.repository.Poster.PosterRepository;
import com.example.mbti.service.impl.PosterServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
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
    void 심리테스트_유형_추가() throws ParseException, JsonProcessingException {
        //given
        Poster findTitle = Poster.builder()
                .posterTitle(null)
                .imgUrl(null)
                .passage(null)
                .build();

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
        when(posterRepository.findByTitle(dto.getPosterTitle())).thenReturn(Optional.ofNullable(findTitle));

        //when
        HashMap<String, Object> stringObjectHashMap = posterService.addPost(dto);

        //then
        Object dataObject = stringObjectHashMap.get("data");
        objectMapper = new ObjectMapper();
        String objectToString = objectMapper.writeValueAsString(dataObject);

        //2. String JSOn으로 변환 Object -> JSONArray -> JSONObject
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(objectToString);
        JSONObject jsonObject = (JSONObject) obj;

        String responsePosterTitle = (String) jsonObject.get("posterTitle");
        String responseImgUrl = (String) jsonObject.get("imgUrl");
        String responsePassage = (String) jsonObject.get("passage");

        assertThat(responsePosterTitle).isEqualTo(dto.getPosterTitle());
        assertThat(responseImgUrl).isEqualTo(dto.getImgUrl());
        assertThat(responsePassage).isEqualTo(dto.getPassage());
    }

    @Test
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
        List<PosterResponseDto> postList = posterService.findPost();

        //then
        assertThat(postList.size()).isEqualTo(5);
        assertThat(postList.get(1).getPosterTitle()).isEqualTo("심리테스트 유형1");
        assertThat(postList.get(2).getImgUrl()).isEqualTo("심리테스트 유형 이미지 주소2");
        assertThat(postList.get(3).getPassage()).isEqualTo("심리테스트 지문3");
        assertThat(postList).isNotEmpty();
    }

    @Test
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