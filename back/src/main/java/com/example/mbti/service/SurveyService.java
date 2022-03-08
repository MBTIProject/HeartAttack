package com.example.mbti.service;

import com.example.mbti.dto.SurveyRequestDto;
import com.example.mbti.dto.SurveyResponseDto;

import java.util.List;

public interface SurveyService {

    int update(Long posterId,Long surveyId);
    void addSurvey(SurveyRequestDto surveyRequestDto, Long posterId);
    List<SurveyResponseDto> findPost(Long posterId);
}
