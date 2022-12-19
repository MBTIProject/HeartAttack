package com.example.mbti.service;

import com.example.mbti.dto.request.PosterRequestDto;
import com.example.mbti.dto.response.PosterResponseDto;
import com.example.mbti.model.Poster;
import com.example.mbti.repository.Poster.PosterRepository;
import com.example.mbti.service.impl.PosterServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PosterServiceTest {

    @InjectMocks
    private PosterServiceImpl posterService;

    @Mock
    private PosterRepository posterRepository;

    @Test
    void 심리테스트_유형_추가() {
        //given
        PosterRequestDto dto = new PosterRequestDto();
        dto.setPosterTitle("심리테스트 유형1");
        dto.setImgUrl("심리테스트 유형 주소1");
        dto.setPassage("지문1");

        Poster poster = Poster.builder()
                .posterTitle(dto.getPosterTitle())
                .imgUrl(dto.getImgUrl())
                .passage(dto.getPassage())
                .build();

        //stub
        when(posterRepository.save(any())).thenReturn(poster);
        when(posterRepository.findByTitle(dto.getPosterTitle())).thenReturn(java.util.Optional.of(poster));

        //when
        PosterResponseDto posterResponseDto = posterService.addPost(dto);

        //then
        assertThat(posterResponseDto.getPosterTitle()).isEqualTo(dto.getPosterTitle());
        assertThat(posterResponseDto.getImg_url()).isEqualTo(dto.getImgUrl());
        assertThat(posterResponseDto.getPassage()).isEqualTo(dto.getPassage());
    }
}