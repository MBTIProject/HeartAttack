package com.example.mbti.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PosterRequestDto {
    private String imgUrl;
    private String posterTitle;
    private String passage;
}