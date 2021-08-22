package com.example.mbti.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyRequestDto {
    private String quiz;
    private String selection;
    private String answer;
    private Long poster_id;
    private int answer_cnt;
}
