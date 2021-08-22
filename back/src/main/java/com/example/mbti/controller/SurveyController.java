package com.example.mbti.controller;

import com.example.mbti.dto.SurveyRequestDto;
import com.example.mbti.model.Survey;
import com.example.mbti.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyRepository surveyRepository;

    //심리테스트 선택지, 결과 등록
    @PostMapping("survey/list")
    public Survey createSurvey(@RequestBody SurveyRequestDto surveyRequestDto) {
        Survey survey = new Survey(surveyRequestDto);
        return surveyRepository.save(survey);
    }
    //심리테스트 선택지, 결과 조회
    @GetMapping("survey/list")
    public List<Survey> selectPost() {
        return surveyRepository.findAll();
    }

}
