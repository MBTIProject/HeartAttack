package com.example.mbti.dto;

import com.example.mbti.model.Poster;
import com.example.mbti.model.Survey;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PosterResponseDto {

    private Long poster_id;

    private String poster_title;
    private String img_url;
    private int poster_view_count;

    private LocalDateTime date;

    private String passage;
    
   // private List<SurveyResponseDto> surveyResponseDto;
    
    @Builder
    public PosterResponseDto(final Long poster_id, final String poster_title, final String img_url, final int poster_view_count, final List<SurveyResponseDto> survey_id){
        this.poster_id = poster_id;
        this.poster_title = poster_title;
        this.img_url = img_url;
        this.poster_view_count = poster_view_count;
       // this.surveyResponseDto = survey_id;
    }

    public PosterResponseDto(Poster poster) {
        this.poster_id = poster.getId();
        this.poster_title = poster.getPoster_title();
        this.img_url = poster.getImg_url();
        this.poster_view_count = poster.getPoster_view_count();
        this.date = poster.getModifiedAt();
        this.passage = poster.getPassage();

    }

    public static PosterResponseDto of(final List<Survey> surveyList, final Poster poster){
        return PosterResponseDto.builder()
                .poster_id(poster.getId())
                .poster_title(poster.getPoster_title())
                .img_url(poster.getImg_url())
                .poster_view_count(poster.getPoster_view_count())
                .survey_id(surveyList.stream()
                .map(SurveyResponseDto::new)
                .collect(Collectors.toList()))
                .build();
    }
}
