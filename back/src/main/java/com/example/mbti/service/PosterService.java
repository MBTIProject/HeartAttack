package com.example.mbti.service;

import com.example.mbti.dto.request.PosterRequestDto;
import com.example.mbti.dto.response.PosterResponseDto;
import com.example.mbti.model.Poster;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface PosterService {
    HashMap<String, Object> addPost(PosterRequestDto posterRequestDto);
    List<PosterResponseDto> findPost();
    Optional<Poster> modifyPostCnt(Long posterId);

}
