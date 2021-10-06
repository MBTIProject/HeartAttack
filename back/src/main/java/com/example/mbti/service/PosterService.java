package com.example.mbti.service;

import com.example.mbti.repository.PosterRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Getter
@RequiredArgsConstructor
@Service
public class PosterService {
    private final PosterRepository posterRepository;

    @Transactional
    public Long update(Long poster_id){
        posterRepository.updatePoster(poster_id);
        return poster_id;
    }
}
