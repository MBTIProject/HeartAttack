package com.example.mbti.advice;

import com.example.mbti.dto.CommentResponseDto;
import com.example.mbti.dto.PosterResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetAllComment {

    private boolean success;

    private String msg;

    private List<CommentResponseDto> data;
}
