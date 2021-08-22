package com.example.mbti.controller;

import com.example.mbti.dto.SurveyRequestDto;
import com.example.mbti.model.Survey;
import com.example.mbti.repository.SurveyRepository;
import com.example.mbti.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyRepository surveyRepository;
    private final SurveyService surveyService;

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

    //심리테스트 결과 조회수
    @PutMapping("survey/list/{id}")
    public Long updatePoster(@PathVariable Long id){
        surveyService.update(id);
        return id;
    }

}
