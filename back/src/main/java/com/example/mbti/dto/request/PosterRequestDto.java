package com.example.mbti.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PosterRequestDto {
    private String img_url;
    private String poster_title;
    private String passage;
}