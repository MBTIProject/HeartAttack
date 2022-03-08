package com.example.mbti.service.impl;

import com.example.mbti.dto.request.CommentRequestDto;
import com.example.mbti.dto.response.CommentResponseDto;
import com.example.mbti.model.Comment;
import com.example.mbti.model.Poster;
import com.example.mbti.repository.CommentRepository;
import com.example.mbti.repository.Poster.PosterRepository;
import com.example.mbti.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final PosterRepository posterRepository;
    @Override
    @Transactional(readOnly = false)
    public void addComment(Long posterId, CommentRequestDto commentRequestDto) {
        Optional<Poster> findPosterId = posterRepository.findById(posterId);
        Comment comment = Comment.builder()
                .comment(commentRequestDto.getComment())
                .poster(findPosterId.get())
                .build();
        commentRepository.save(comment);
    }

    @Override
    public List<CommentResponseDto> findComment(Long posterId) {
        return commentRepository.findByPosterId(posterId).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
