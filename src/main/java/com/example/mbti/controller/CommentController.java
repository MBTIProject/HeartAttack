package com.example.mbti.controller;

import com.example.mbti.advice.ResultInfo;
import com.example.mbti.dto.request.CommentRequestDto;
import com.example.mbti.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{posterId}")
public class CommentController {

    private final CommentService commentService;

    //심리테스트 유형별 댓글 추가
    @PostMapping("/comments")
    public ResultInfo createComment(@RequestBody CommentRequestDto commentRequestDto,
                                    @PathVariable Long posterId) {
        return new ResultInfo(ResultInfo.Code.CREATE, "댓글 등록 성공!", commentService.addComment(posterId, commentRequestDto));
    }

    //심리테스트 유형별 댓글 조회
    @GetMapping("/comments")
    public ResultInfo selectComment(@PathVariable Long posterId) {
        return new ResultInfo(ResultInfo.Code.SUCCESS,"댓글 조회 성공!", commentService.findComment(posterId));
    }
}