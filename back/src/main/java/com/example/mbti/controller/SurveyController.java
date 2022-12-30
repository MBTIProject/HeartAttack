package com.example.mbti.controller;

import com.example.mbti.advice.ResultInfo;
import com.example.mbti.dto.request.SurveyRequestDto;
import com.example.mbti.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyController {
    private final SurveyService surveyService;

    //심리테스트 지문 등록
    @PostMapping("/{posterId}")
    public ResultInfo createSurvey(@RequestBody SurveyRequestDto surveyRequestDto,
                                   @PathVariable Long posterId) {
        return new ResultInfo(ResultInfo.Code.CREATE,"심리테스트 지문 등록 성공!", surveyService.addSurvey(surveyRequestDto, posterId));
    }
    //심리테스트 선택지, 결과 조회
    @GetMapping("/{posterId}")
    public ResultInfo PostList(@PathVariable Long posterId) {
        return new ResultInfo(ResultInfo.Code.SUCCESS,"심리테스트 지문 조회",surveyService.findPost(posterId));
    }

    //심리테스트 결과 조회수
    @PutMapping("/{surveyId}")
    public ResultInfo updatePoster(@PathVariable Long surveyId){
        return new ResultInfo(ResultInfo.Code.SUCCESS,"심리테스트 결과 조회수 증가 성공", surveyService.update(surveyId));
    }

}
