package com.example.mbti.service.impl;

import com.example.mbti.advice.exception.ApiRequestException;
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

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.mbti.advice.ResultInfo.makeResultMap;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final PosterRepository posterRepository;
    @Override
    @Transactional
    public CommentResponseDto addComment(Long posterId, CommentRequestDto commentRequestDto) {
        Optional<Poster> optPoster = Optional.ofNullable(posterRepository.findById(posterId).orElseThrow(() -> {
            throw new ApiRequestException("존재하지 않는 심리테스트 유형입니다.");
        }));
        return new CommentResponseDto(commentRepository.save(commentRequestDto.toEntity(optPoster.get())));
    }

    @Override
    public CommentResponseDto.CommentList findComment(Long posterId) {
        List<CommentResponseDto> commentList = commentRepository.findByPosterPosterId(posterId).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
        return new CommentResponseDto.CommentList(commentList);
    }
}
