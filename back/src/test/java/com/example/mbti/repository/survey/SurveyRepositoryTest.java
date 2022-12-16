package com.example.mbti.repository.survey;

import com.example.mbti.model.Poster;
import com.example.mbti.repository.Poster.PosterRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SurveyRepositoryTest {

    @Autowired // DI
    private PosterRepository posterRepository;

    @Test
    void 심리테스트_유형_등록(){
        //given
        String posterTitle = "심리테스트 유형 제목1";
        String imgUrl = "심리테스트 유형 주소1";
        String passage = "심리테스트 유형 지문";
        Poster poster = Poster.builder()
                .posterTitle(posterTitle)
                .imgUrl(imgUrl)
                .passage(passage)
                .build();

        //when
        Poster savePoster = posterRepository.save(poster);

        //then
        Assertions.assertThat(savePoster.getPosterTitle()).isEqualTo(posterTitle);
        Assertions.assertThat(savePoster.getImgUrl()).isEqualTo(imgUrl);
        Assertions.assertThat(savePoster.getPassage()).isEqualTo(passage);
    }

    @Test
    void 제목으로조회(){
        //given
        String posterTitle = "심리테스트 유형 제목1";
        String imgUrl = "심리테스트 유형 주소1";
        String passage = "심리테스트 유형 지문";
        Poster poster = Poster.builder()
                .posterTitle(posterTitle)
                .imgUrl(imgUrl)
                .passage(passage)
                .build();
        Poster savePoster = posterRepository.save(poster);

        //when
        Optional<Poster> optPoster = posterRepository.findByTitle(savePoster.getPosterTitle());
        
        //then
        Assertions.assertThat(optPoster.get().getPosterTitle()).isEqualTo(posterTitle);
        Assertions.assertThat(optPoster.get().getImgUrl()).isEqualTo(imgUrl);
        Assertions.assertThat(optPoster.get().getPassage()).isEqualTo(passage);
        Assertions.assertThat(optPoster.get().getPosterId()).isEqualTo(savePoster.getPosterId());
    }
}