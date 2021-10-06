package com.example.mbti.controller;

import com.example.mbti.dto.CommentRequestDto;
import com.example.mbti.model.Comment;
import com.example.mbti.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    //심리테스트 유형별 댓글 추가
    @PostMapping("/comment/list")
    public Comment createComment(@RequestBody CommentRequestDto commentRequestDto){
        Comment comment = new Comment(commentRequestDto);
        return commentRepository.save(comment);
    }

    //심리테스트 유형별 댓글 조회
    @GetMapping("/comment/list/{poster_id}")
    public List<Comment> selectComment(@PathVariable Long poster_id) {
          return commentRepository.findByPoster_id(poster_id);
    }
}
