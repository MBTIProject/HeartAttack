package com.example.mbti.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentRequestDto {
    private  String name;

    private String comment;

    private Long poster_id;
}