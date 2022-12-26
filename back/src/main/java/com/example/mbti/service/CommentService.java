package com.example.mbti.service;

import com.example.mbti.dto.request.CommentRequestDto;
import com.example.mbti.dto.response.CommentResponseDto;

import java.util.HashMap;
import java.util.List;

public interface CommentService {
    String addComment(Long posterId, CommentRequestDto commentRequestDto);
    HashMap<String, Object> findComment(Long posterId);

}
