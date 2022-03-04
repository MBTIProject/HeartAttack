package com.example.mbti.service;

import com.example.mbti.advice.exception.ApiRequestException;
import com.example.mbti.dto.PosterRequestDto;
import com.example.mbti.dto.PosterResponseDto;
import com.example.mbti.model.Poster;
import com.example.mbti.repository.PosterRepository;
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

//    public Long update(Long poster_id){
//        posterRepository.updatePoster(poster_id);
//        return poster_id;
//    }

    @Transactional(readOnly = false)
    public void addPost(PosterRequestDto posterRequestDto) {

        Optional<Poster> findPosterTitle = posterRepository.findByTitle(posterRequestDto.getPoster_title());
        if(!findPosterTitle.isPresent()) {
            Poster poster = Poster.builder()
                    .poster_title(posterRequestDto.getPoster_title())
                    .img_url(posterRequestDto.getImg_url())
                    .quiz(posterRequestDto.getQuiz())
                    .build();
            posterRepository.save(poster);
        }
        else {
            throw new ApiRequestException("동일한 심리테스트 제목이 존재합니다.");
        }
    }

    public List<PosterResponseDto> findPost() {
        List<PosterResponseDto> allPost = posterRepository.findAll().stream()
                .map(PosterResponseDto::new)
                .collect(Collectors.toList());
        return allPost;
    }
}
