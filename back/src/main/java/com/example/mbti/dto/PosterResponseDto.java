package com.example.mbti.dto;

import com.example.mbti.model.Poster;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PosterResponseDto {

    private String poster_title;

    private String quiz;

    private String img_url;

    public PosterResponseDto(Poster poster) {
        this.poster_title = poster.getPoster_title();
        this.quiz = poster.getQuiz();
        this.img_url = poster.getImg_url();
    }
}
