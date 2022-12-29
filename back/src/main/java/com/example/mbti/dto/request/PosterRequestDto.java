package com.example.mbti.dto.request;

import com.example.mbti.model.Poster;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class PosterRequestDto {
    private String imgUrl;
    private String posterTitle;
    private String passage;

    public Poster toEntity(){
        return Poster.builder()
                .posterTitle(posterTitle)
                .imgUrl(imgUrl)
                .passage(passage)
                .build();
    }
}