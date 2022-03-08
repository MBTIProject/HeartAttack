package com.example.mbti.controller;

import com.example.mbti.advice.GetAllSurvey;
import com.example.mbti.advice.Success;
import com.example.mbti.dto.SurveyRequestDto;
import com.example.mbti.dto.SurveyResponseDto;
import com.example.mbti.model.Survey;
import com.example.mbti.repository.survey.SurveyRepository;
import com.example.mbti.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyController {
    private final SurveyService surveyService;

    //심리테스트 지문 등록
    @PostMapping("/{posterId}")
    public ResponseEntity<Success> createSurvey(@RequestBody SurveyRequestDto surveyRequestDto, @PathVariable Long posterId) {
        surveyService.addSurvey(surveyRequestDto, posterId);
        return new ResponseEntity<>(new Success(true,"심리테스트 지문 등록 성공!"), HttpStatus.OK);
    }
    //심리테스트 선택지, 결과 조회
    @GetMapping("/{posterId}")
    public ResponseEntity<GetAllSurvey> PostList(@PathVariable Long posterId) {
        return new ResponseEntity<>(new GetAllSurvey(true,"심리테스트 지문 조회",surveyService.findPost(posterId)), HttpStatus.OK);
    }

    //심리테스트 결과 조회수
    @PutMapping("/{posterId}/{surveyId}")
    public ResponseEntity<Success> updatePoster(@PathVariable Long posterId, @PathVariable Long surveyId){
        surveyService.update(posterId,surveyId);
        return new ResponseEntity<>(new Success(true,"심리테스트 결과 조회수 증가 성공"),HttpStatus.OK);
    }

}
