package com.example.mbti.repository.survey;

import com.example.mbti.model.Poster;
import com.example.mbti.repository.Poster.PosterRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

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
                .poster_title(posterTitle)
                .img_url(imgUrl)
                .passage(passage)
                .build();

        //when
        Poster savePoster = posterRepository.save(poster);

        //then
        Assertions.assertThat(savePoster.getPoster_title()).isEqualTo(posterTitle);
        Assertions.assertThat(savePoster.getImg_url()).isEqualTo(imgUrl);
        Assertions.assertThat(savePoster.getPassage()).isEqualTo(passage);
    }
}