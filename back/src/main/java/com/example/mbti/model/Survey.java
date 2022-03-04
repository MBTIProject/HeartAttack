package com.example.mbti.model;

import com.example.mbti.dto.SurveyRequestDto;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Survey extends Timestamped  {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Poster poster;

    @Column(nullable = false)
    private String selection;

    @Column(nullable = false)
    private String answer_title;  //추가

    @Column(columnDefinition = "TEXT",nullable = false)
    private String answer;

    public Survey(SurveyRequestDto surveyRequestDto) {
        this.selection = surveyRequestDto.getSelection();
        this.answer = surveyRequestDto.getAnswer();
        this.answer_title = surveyRequestDto.getAnswer_title();
    }

    @Builder
    public Survey (String selection, String answer_title, String answer){
        this.selection = selection;
        this.answer_title = answer_title;
        this.answer = answer;
    }
}