package com.example.mbti.dto.response;

import com.example.mbti.model.Poster;
import com.example.mbti.model.Survey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PosterResponseDto {

    private Long posterId;

    private String posterTitle;
    private String img_url;
    private int posterViewCount;

    private LocalDateTime date;

    private String passage;
    
   // private List<SurveyResponseDto> surveyResponseDto;
    
    @Builder
    public PosterResponseDto(final Long poster_id, final String poster_title, final String img_url, final int poster_view_count, final List<SurveyResponseDto> survey_id){
        this.posterId = poster_id;
        this.posterTitle = poster_title;
        this.img_url = img_url;
        this.posterViewCount = poster_view_count;
       // this.surveyResponseDto = survey_id;
    }

    public PosterResponseDto(Poster poster) {
        this.posterId = poster.getPosterId();
        this.posterTitle = poster.getPosterTitle();
        this.img_url = poster.getImgUrl();
        this.posterViewCount = poster.getPosterViewCount();
        this.date = poster.getModifiedAt();
        this.passage = poster.getPassage();

    }

    public static PosterResponseDto of(final List<Survey> surveyList, final Poster poster){
        return PosterResponseDto.builder()
                .poster_id(poster.getPosterId())
                .poster_title(poster.getPosterTitle())
                .img_url(poster.getImgUrl())
                .poster_view_count(poster.getPosterViewCount())
                .survey_id(surveyList.stream()
                .map(SurveyResponseDto::new)
                .collect(Collectors.toList()))
                .build();
    }
}
