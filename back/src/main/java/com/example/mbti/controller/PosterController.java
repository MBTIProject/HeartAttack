package com.example.mbti.controller;

import com.example.mbti.dto.PosterRequestDto;
import com.example.mbti.model.Poster;
import com.example.mbti.repository.PosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PosterController {
    private final PosterRepository posterRepository;

    @PostMapping("/main/list")
    public Poster createPost(@RequestBody PosterRequestDto posterRequestDto){
        Poster poster = new Poster(posterRequestDto);
        return posterRepository.save(poster);
    }
}
