package com.example.mbti.dto.response;

import com.example.mbti.model.Survey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyResponseDto {
    private String choice;

    private String choiceResult;

    private int choiceViewCount;

    public SurveyResponseDto(Survey survey) {
        this.choice = survey.getChoice();
        this.choiceResult = survey.getChoiceResult();
        this.choiceViewCount = survey.getChoiceViewCount();
    }

    @Getter
    public static class SurveyList{
        private List<SurveyResponseDto> surveyList = new ArrayList<>();

        public SurveyList(List<SurveyResponseDto> surveyList) {
            this.surveyList = surveyList;
        }
    }
}
