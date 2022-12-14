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

    private String choiceResult;

    private int choiceViewCount;

    private String passage;

    public SurveyResponseDto(Survey survey) {
        this.choice = survey.getChoice();
        this.choiceResult = survey.getChoiceResult();
        this.choiceViewCount = survey.getChoiceViewCount();
        this.passage = survey.getPoster().getPassage();
    }
}
