package com.example.mbti.controller;

import com.example.mbti.advice.ResultInfo;
import com.example.mbti.dto.request.PosterRequestDto;
import com.example.mbti.service.PosterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PosterController {
    private final PosterService posterService;

    //심리테스트 유형 추가
    @PostMapping("")
    public ResultInfo createPost(@RequestBody PosterRequestDto posterRequestDto){
        return new ResultInfo(ResultInfo.Code.SUCCESS,"심리테스트 유형 등록 성공!", posterService.addPost(posterRequestDto));
    }

    //심리테스트 유형 전체조회
    @GetMapping("")
    public ResultInfo selectPost() {
        return new ResultInfo(ResultInfo.Code.SUCCESS,"심리테스트 유형 전제조회 완료", posterService.findPost());
    }

    //심리테스트 유형 조회수 증가
    @PutMapping("/{posterId}")
    public ResultInfo PostViewCnt(@PathVariable Long posterId){
        posterService.modifyPostCnt(posterId);
        return new ResultInfo(ResultInfo.Code.SUCCESS,"심리테스트 조회수 증가 성공!");
    }
}
