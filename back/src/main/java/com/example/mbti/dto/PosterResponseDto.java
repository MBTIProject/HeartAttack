package com.example.mbti.dto;

import com.example.mbti.model.Poster;
import com.example.mbti.model.Survey;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PosterResponseDto {

    private String poster_title;
    private String img_url;
    private int poster_view_count;
    
    private List<SurveyResponseDto> surveyResponseDto;
    
    @Builder
    public PosterResponseDto(final String poster_title, final String img_url, final int poster_view_count, final List<SurveyResponseDto> poster_id){
        this.poster_title = poster_title;
        this.img_url = img_url;
        this.poster_view_count = poster_view_count;
        this.surveyResponseDto = poster_id;
    }

    public static PosterResponseDto of(final List<Survey> surveyList, final Poster poster){
        return PosterResponseDto.builder()
                .poster_title(poster.getPoster_title())
                .img_url(poster.getImg_url())
                .poster_view_count(poster.getPoster_view_count())
                .poster_id(surveyList.stream()
                .map(SurveyResponseDto::new)
                .collect(Collectors.toList()))
                .build();
    }
}
