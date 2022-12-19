package com.example.mbti.repository.Poster;

import com.example.mbti.dto.response.PosterResponseDto;
import com.example.mbti.model.Poster;
import com.example.mbti.model.Survey;
import com.example.mbti.repository.survey.SurveyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PosterRepositoryTest {

    @Autowired
    private PosterRepository posterRepository;

    @Autowired
    private SurveyRepository surveyRepository;

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

        String choice = "선택지";
        String choiceResult ="결과";
        List<Survey> surveyList = new ArrayList<>();
        for(int i=0; i<5; i++){
            Survey survey = Survey.builder()
                    .poster(poster)
                    .choiceResult(choiceResult+i)
                    .choice(choice+i)
                    .build();
            surveyList.add(survey);
        }
        surveyRepository.saveAll(surveyList);
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

    @Test
    void 심리테스트_유형추가(){
        //given
        String posterTitle = "심리테스트 유형 제목2";
        String passage = "지문2";
        String imgUrl = "심리테스트 이미지주소2";

        Poster poster = Poster.builder()
                .posterTitle(posterTitle)
                .imgUrl(imgUrl)
                .passage(passage)
                .build();

        //when
        Poster savePoster = posterRepository.save(poster);

        //then
        assertThat(savePoster.getPosterTitle()).isEqualTo(posterTitle);
        assertThat(savePoster.getPassage()).isEqualTo(passage);
        assertThat(savePoster.getImgUrl()).isEqualTo(imgUrl);
    }

    @Test
    void 심리테스트유형_전체조회(){

        //when
        List<Poster> allPosterAndSurvey = posterRepository.findAllPosterAndSurvey();

        //then
        assertThat(allPosterAndSurvey.size()).isEqualTo(1);
        assertThat(allPosterAndSurvey.get(0).getSurveyList().size()).isEqualTo(5);
    }
}