package com.example.mbti.dto.response;

import com.example.mbti.model.Poster;
import com.example.mbti.model.Survey;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PosterResponseDto {

    private Long posterId;
    private String posterTitle;
    private String imgUrl;
    private int posterViewCount;
    private LocalDateTime createDate;
    private String passage;
    private List<SurveyResponseDto> surveyResponseDto;
    
    @Builder
    public PosterResponseDto(final Long poster_id, final String poster_title, final String img_url, final int poster_view_count, final List<SurveyResponseDto> survey_id){
        this.posterId = poster_id;
        this.posterTitle = poster_title;
        this.imgUrl = img_url;
        this.posterViewCount = poster_view_count;
    }

    public PosterResponseDto(Poster poster) {
        this.posterId = poster.getPosterId();
        this.posterTitle = poster.getPosterTitle();
        this.imgUrl = poster.getImgUrl();
        this.posterViewCount = poster.getPosterViewCount();
        this.createDate = poster.getModifiedAt();
        this.surveyResponseDto = poster.getSurveyList().stream()
                .map(SurveyResponseDto::new)
                .collect(Collectors.toList());
        this.passage = poster.getPassage();
    }

    @Getter
    public static class PosterList{
        private List<PosterResponseDto> posterList = new ArrayList<>();

        public PosterList(List<PosterResponseDto> posterList) {
            this.posterList = posterList;
        }
    }

    public static PosterResponseDto of(final List<Survey> surveyList, final Poster poster){
        return new PosterResponseDto(poster.getPosterId(), poster.getPosterTitle(), poster.getImgUrl(), poster.getPosterViewCount(), surveyList.stream()
                .map(SurveyResponseDto::new)
                .collect(Collectors.toList()));
    }
}
