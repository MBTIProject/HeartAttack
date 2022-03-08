package com.example.mbti.dto.response;

import com.example.mbti.model.Survey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyResponseDto {
    private String choice;

    private String choice_result;

    private int choice_view_count;

    private String passage;

    public SurveyResponseDto(Survey survey) {
        this.choice = survey.getChoice();
        this.choice_result = survey.getChoice_result();
        this.choice_view_count = survey.getChoice_view_count();
        this.passage = survey.getPoster().getPassage();
    }
}
