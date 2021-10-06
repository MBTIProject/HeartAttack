package com.example.mbti.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyRequestDto {
    private String selection;
    private int selection_id;
    private String answer;
    private String answer_title;
    private Long poster_id;
    private int answer_cnt;
}
