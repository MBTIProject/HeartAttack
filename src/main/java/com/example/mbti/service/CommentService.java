package com.example.mbti.service;

import com.example.mbti.dto.request.CommentRequestDto;
import com.example.mbti.dto.response.CommentResponseDto;

import java.util.HashMap;
import java.util.List;

public interface CommentService {
    CommentResponseDto addComment(Long posterId, CommentRequestDto commentRequestDto);
    CommentResponseDto.CommentList findComment(Long posterId);

}
