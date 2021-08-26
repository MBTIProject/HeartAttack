package com.example.mbti.controller;

import com.example.mbti.dto.PosterRequestDto;
import com.example.mbti.model.Poster;
import com.example.mbti.repository.PosterRepository;
import com.example.mbti.service.PosterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PosterController {
    private final PosterRepository posterRepository;
    private final PosterService posterService;

    //심리테스트 유형 추가
    @PostMapping("/main/list")
    public Poster createPost(@RequestBody PosterRequestDto posterRequestDto){
        Poster poster = new Poster(posterRequestDto);
        return posterRepository.save(poster);
    }

    //심리테스트 유형 전체조회
    @GetMapping("/main/list")
    public List<Poster> selectPost() {
        return posterRepository.findAll();
    }

    //심리테스트 유형별 조회수
    @PutMapping("/main/list/{id}")
    public Long updatePoster(@PathVariable Long id){
        posterService.update(id);
        return id;
    }
}
