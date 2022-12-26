package com.example.mbti.service;

import com.example.mbti.dto.request.SurveyRequestDto;
import com.example.mbti.dto.response.SurveyResponseDto;

import java.util.HashMap;
import java.util.List;

public interface SurveyService {

    int update(Long posterId,Long surveyId);
    SurveyResponseDto addSurvey(SurveyRequestDto surveyRequestDto, Long posterId);
    HashMap<String, Object> findPost(Long posterId);
}
