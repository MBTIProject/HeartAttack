package com.example.mbti.service;

import com.example.mbti.dto.PosterRequestDto;
import com.example.mbti.dto.PosterResponseDto;
import com.example.mbti.model.Poster;

import java.util.List;
import java.util.Optional;

public interface PosterService {
    void addPost(PosterRequestDto posterRequestDto);
    List<PosterResponseDto> findPost();
    Optional<Poster> modifyPostCnt(Long posterId);

}
