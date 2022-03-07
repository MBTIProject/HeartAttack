package com.example.mbti.service;

import com.example.mbti.advice.exception.ApiRequestException;
import com.example.mbti.dto.PosterRequestDto;
import com.example.mbti.dto.PosterResponseDto;
import com.example.mbti.model.Poster;
import com.example.mbti.repository.Poster.PosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PosterService {
    private final PosterRepository posterRepository;

    @Transactional(readOnly = false)
    public void addPost(PosterRequestDto posterRequestDto) {

        Optional<Poster> findPosterTitle = posterRepository.findByTitle(posterRequestDto.getPoster_title());
        if(!findPosterTitle.isPresent()) {
            Poster poster = Poster.builder()
                    .poster_title(posterRequestDto.getPoster_title())
                    .img_url(posterRequestDto.getImg_url())
                    .build();
            posterRepository.save(poster);
        }
        else {
            throw new ApiRequestException("동일한 심리테스트 제목이 존재합니다.");
        }
    }

    public PosterResponseDto findPost() {
        return posterRepository.findPosterList();
    }

    @Transactional(readOnly = false)
    public Optional<Poster> modifyPostCnt(Long posterId) {
        Optional<Poster> findPostId = Optional.ofNullable(posterRepository.findById(posterId).orElseThrow(()
                -> new ApiRequestException("존재하지 않는 유형입니다.")));
        findPostId.get().updatePostCnt();
        return findPostId;
    }
}
