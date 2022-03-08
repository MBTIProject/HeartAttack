package com.example.mbti.controller;

import com.example.mbti.advice.GetAllComment;
import com.example.mbti.advice.Success;
import com.example.mbti.dto.CommentRequestDto;
import com.example.mbti.dto.CommentResponseDto;
import com.example.mbti.model.Comment;
import com.example.mbti.repository.CommentRepository;
import com.example.mbti.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{posterId}")
public class CommentController {

    private final CommentService commentService;

    //심리테스트 유형별 댓글 추가
    @PostMapping("/comments")
    public ResponseEntity<Success> createComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long posterId) {
        commentService.addComment(posterId, commentRequestDto);
        return new ResponseEntity<>(new Success(true, "댓글 등록 성공!"), HttpStatus.OK);
    }

    //심리테스트 유형별 댓글 조회
    @GetMapping("/comments")
    public ResponseEntity<GetAllComment> selectComment(@PathVariable Long posterId) {
        return new ResponseEntity<>(new GetAllComment(true,"댓글 조회 성공!",commentService.findComment(posterId)),HttpStatus.OK);
    }
}