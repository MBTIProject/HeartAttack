package com.example.mbti.service.impl;

import com.example.mbti.advice.exception.ApiRequestException;
import com.example.mbti.dto.request.PosterRequestDto;
import com.example.mbti.dto.response.PosterResponseDto;
import com.example.mbti.model.Poster;
import com.example.mbti.repository.Poster.PosterRepository;
import com.example.mbti.service.PosterService;
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
public class PosterServiceImpl implements PosterService {
    private final PosterRepository posterRepository;

    @Override
    @Transactional
    public PosterResponseDto addPost(PosterRequestDto posterRequestDto) {
        Optional<Poster> optPoster = posterRepository.findByPosterTitle(posterRequestDto.getPosterTitle());
        if(!optPoster.isEmpty()){
            throw new ApiRequestException("동일한 심리테스트 제목이 존재합니다.");
        }else {
            return new PosterResponseDto(posterRepository.save(posterRequestDto.toEntity()));
        }
    }

    @Override
    public PosterResponseDto.PosterList findPost() {
        List<PosterResponseDto> posterList = posterRepository.findAllPosterAndSurvey().stream()
                .map(PosterResponseDto::new)
                .collect(Collectors.toList());
        return new PosterResponseDto.PosterList(posterList);
    }

    @Override
    @Transactional(readOnly = false)
    public Optional<Poster> modifyPostCnt(Long posterId) {
        Optional<Poster> findPostId = Optional.ofNullable(posterRepository.findById(posterId).orElseThrow(()
                -> new ApiRequestException("존재하지 않는 유형입니다.")));
        findPostId.get().updatePostCnt();
        return findPostId;
    }
}
