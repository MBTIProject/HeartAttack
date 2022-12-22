package com.example.mbti.dto.request;

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
}
