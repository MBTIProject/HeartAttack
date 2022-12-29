package com.example.mbti.dto.request;

import com.example.mbti.model.Poster;
import com.example.mbti.model.Survey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
@Setter
public class SurveyRequestDto {
    private String choice;

    private String choiceResult;

    public Survey toEntity(Poster poster){
        return Survey.builder()
                .choice(choice)
                .choiceResult(choiceResult)
                .poster(poster)
                .build();
    }
}
