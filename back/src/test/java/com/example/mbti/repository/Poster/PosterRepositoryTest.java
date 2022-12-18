package com.example.mbti.repository.Poster;

import com.example.mbti.model.Poster;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PosterRepositoryTest {

    @Autowired
    private PosterRepository posterRepository;

    @BeforeEach
    void 데이터준비(){
        String posterTitle = "심리테스트 유형 제목";
        String passage = "지문";
        String imgUrl = "심리테스트 이미지주소";

        Poster poster = Poster.builder()
                .posterTitle(posterTitle)
                .imgUrl(imgUrl)
                .passage(passage)
                .build();
        posterRepository.save(poster);
    }

    @Test
    void 심리테스트제목으로조회() {
        //given

        //when
        Optional<Poster> optPoster = posterRepository.findByTitle("심리테스트 유형 제목");

        //then
        assertThat(optPoster.get().getPosterTitle()).isEqualTo("심리테스트 유형 제목");
        assertThat(optPoster.get().getPassage()).isEqualTo("지문");
        assertThat(optPoster.get().getImgUrl()).isEqualTo("심리테스트 이미지주소");
    }
}