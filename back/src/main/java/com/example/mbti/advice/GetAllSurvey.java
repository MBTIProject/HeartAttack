package com.example.mbti.advice;

import com.example.mbti.dto.PosterResponseDto;
import com.example.mbti.dto.SurveyResponseDto;
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
