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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PosterServiceImpl implements PosterService {
    private final PosterRepository posterRepository;

    @Override
    @Transactional(readOnly = false)
    public PosterResponseDto addPost(PosterRequestDto posterRequestDto) {

        Optional<Poster> findPosterTitle = posterRepository.findByTitle(posterRequestDto.getPosterTitle());
        if(findPosterTitle.isEmpty()) {
            Poster poster = Poster.builder()
                    .posterTitle(posterRequestDto.getPosterTitle())
                    .imgUrl(posterRequestDto.getImgUrl())
                    .passage(posterRequestDto.getPassage())
                    .build();
            return new PosterResponseDto(posterRepository.save(poster));
        }
        else {
            throw new ApiRequestException("동일한 심리테스트 제목이 존재합니다.");
        }
    }

    @Override
    public List<PosterResponseDto> findPost() {
        return posterRepository.findAllPosterAndSurvey().stream()
                .map(PosterResponseDto::new)
                .collect(Collectors.toList());
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
