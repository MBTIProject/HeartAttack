package com.example.mbti.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class SurveyRequestDto {
    private String choice;

    private String choice_result;
}
