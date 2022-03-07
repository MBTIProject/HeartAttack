package com.example.mbti.controller;

import com.example.mbti.advice.GetAllPost;
import com.example.mbti.advice.Success;
import com.example.mbti.dto.PosterRequestDto;
import com.example.mbti.dto.PosterResponseDto;
import com.example.mbti.model.Poster;
import com.example.mbti.service.PosterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class PosterController {
    private final PosterService posterService;

    //심리테스트 유형 추가
    @PostMapping("/list")
    public ResponseEntity<Success> createPost(@RequestBody PosterRequestDto posterRequestDto){
        posterService.addPost(posterRequestDto);
        return new ResponseEntity<>(new Success(true,"심리테스트 유형 등록 성공!"), HttpStatus.OK);
    }

    //심리테스트 유형 전체조회
    @GetMapping("/list")
    public ResponseEntity<GetAllPost> selectPost() {
        return new ResponseEntity<>(new GetAllPost(true,"심리테스트 유형 전제조회 완료", posterService.findPost()),HttpStatus.OK);
    }

    //심리테스트 유형 조회수 증가
    @PutMapping("/list/{posterId}")
    public ResponseEntity<Success> PostViewCnt(@PathVariable Long posterId){
        posterService.modifyPostCnt(posterId);
        return new ResponseEntity<>(new Success(true,"심리테스트 조회수 증가 성공!"),HttpStatus.OK);
    }
}
