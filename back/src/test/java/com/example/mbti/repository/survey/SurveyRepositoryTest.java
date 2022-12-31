package com.example.mbti.repository.survey;

import com.example.mbti.model.Poster;
import com.example.mbti.model.Survey;
import com.example.mbti.repository.Poster.PosterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SurveyRepositoryTest {

    @Autowired // DI
    private PosterRepository posterRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @BeforeEach
    void 심리테스트_유형_데이터_준비(){
        String posterTitle = "심리테스트 유형 제목1";
        String imgUrl = "심리테스트 유형 주소1";
        String passage = "심리테스트 유형 지문1";

        Poster poster = Poster.builder()
                .posterTitle(posterTitle)
                .imgUrl(imgUrl)
                .passage(passage)
                .build();
        Poster savePoster = posterRepository.save(poster);
    }

    @Test
    @DisplayName("심리테스트_지문_등록")
    void 심리테스트_지문_등록(){
        //given
        String choice = "선택지";
        String choiceResult = "결과";

        Poster poster = posterRepository.findByPosterTitle("심리테스트 유형 제목1").get();
        List<Survey> surveyList = new ArrayList<>();
        for(int i=0; i<5; i++){
            Survey survey = Survey.builder()
                    .poster(poster)
                    .choice(choice + i)
                    .choiceResult(choiceResult + i)
                    .build();
            surveyList.add(survey);
        }
        surveyRepository.saveAll(surveyList);

        //when
        List<Survey> surveys = surveyRepository.findByPosterPosterId(poster.getPosterId());

        //then
        assertThat(surveys.size()).isEqualTo(5);
        assertThat(surveys.get(0).getChoice()).isEqualTo("선택지0");
        assertThat(surveys.get(1).getChoiceResult()).isEqualTo("결과1");

    }

    @Test
    @DisplayName("제목으로조회")
    void 제목으로조회(){
        //given
        String posterTitle = "심리테스트 유형 제목2";
        String imgUrl = "심리테스트 유형 주소2";
        String passage = "심리테스트 유형 지문2";
        Poster poster = Poster.builder()
                .posterTitle(posterTitle)
                .imgUrl(imgUrl)
                .passage(passage)
                .build();
        Poster savePoster = posterRepository.save(poster);

        //when
        Optional<Poster> optPoster = posterRepository.findByPosterTitle(savePoster.getPosterTitle());
        
        //then
        assertThat(optPoster.get().getPosterTitle()).isEqualTo(posterTitle);
        assertThat(optPoster.get().getImgUrl()).isEqualTo(imgUrl);
        assertThat(optPoster.get().getPassage()).isEqualTo(passage);
        assertThat(optPoster.get().getPosterId()).isEqualTo(savePoster.getPosterId());
    }
}