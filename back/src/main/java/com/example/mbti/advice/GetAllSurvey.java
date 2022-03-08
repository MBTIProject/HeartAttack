package com.example.mbti.advice;

import com.example.mbti.dto.response.SurveyResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetAllSurvey {

    private boolean success;

    private String msg;

    private List<SurveyResponseDto> data;
}
