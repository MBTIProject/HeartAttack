package com.example.mbti.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PosterRequestDto {
    private String img_url;
    private String poster_title;
    private int poster_cnt;
    private String quiz;
}