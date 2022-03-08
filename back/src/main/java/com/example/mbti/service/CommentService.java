package com.example.mbti.service;

import com.example.mbti.dto.request.CommentRequestDto;
import com.example.mbti.dto.response.CommentResponseDto;

import java.util.List;

public interface CommentService {
    void addComment(Long posterId, CommentRequestDto commentRequestDto);
    List<CommentResponseDto> findComment(Long posterId);

}
