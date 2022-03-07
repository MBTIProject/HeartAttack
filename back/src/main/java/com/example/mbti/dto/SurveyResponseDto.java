package com.example.mbti.dto;

import com.example.mbti.model.Survey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyResponseDto {
    private String passage;

    public SurveyResponseDto(Survey survey) {
        this.passage = survey.getPassage();
    }
}
