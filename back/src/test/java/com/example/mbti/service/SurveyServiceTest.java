package com.example.mbti.service;

import com.example.mbti.dto.request.SurveyRequestDto;
import com.example.mbti.dto.response.SurveyResponseDto;
import com.example.mbti.model.Poster;
import com.example.mbti.model.Survey;
import com.example.mbti.repository.Poster.PosterRepository;
import com.example.mbti.repository.survey.SurveyRepository;
import com.example.mbti.service.impl.SurveyServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SurveyServiceTest {

    @InjectMocks
    private SurveyServiceImpl surveyService;

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private PosterRepository posterRepository;

    @Test
    @DisplayName("심리테스트_지문_등록")
    void 심리테스트_지문_등록(){
        //given
        String posterTitle = "심리테스트 유형 제목";
        String passage = "지문";
        String imgUrl = "심리테스트 이미지주소";

        Poster poster = Poster.builder()
                .posterId(1L)
                .posterTitle(posterTitle)
                .imgUrl(imgUrl)
                .passage(passage)
                .build();

        SurveyRequestDto surveyRequestDto = new SurveyRequestDto();
        surveyRequestDto.setChoice("선택지1");
        surveyRequestDto.setChoiceResult("결과1");

        Survey survey = Survey.builder()
                .choice(surveyRequestDto.getChoice())
                .choiceResult(surveyRequestDto.getChoiceResult())
                .poster(poster)
                .build();

        //stub
        when(posterRepository.findById(poster.getPosterId())).thenReturn(Optional.of(poster));
        when((surveyRepository.save(any()))).thenReturn(survey);

        //when
        SurveyResponseDto surveyResponseDto = surveyService.addSurvey(surveyRequestDto, poster.getPosterId());

        //then
        assertThat(surveyResponseDto.getChoice()).isEqualTo(surveyRequestDto.getChoice());
        assertThat(surveyResponseDto.getChoiceResult()).isEqualTo(surveyRequestDto.getChoiceResult());
        assertThat(surveyResponseDto.getChoiceViewCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("심리테스트_지문_등록")
    void 심리테스트_선택지_결과_조회() {
        //given
        String posterTitle = "심리테스트 유형 제목";
        String passage = "지문";
        String imgUrl = "심리테스트 이미지주소";

        String choice = "선택지";
        String choiceResult = "결과";

        Poster poster = Poster.builder()
                .posterId(1L)
                .posterTitle(posterTitle)
                .imgUrl(imgUrl)
                .passage(passage)
                .build();

        List<Survey> surveyList = new ArrayList<>();
        Survey survey = Survey.builder()
                .choice(choice)
                .choiceResult(choiceResult)
                .poster(poster)
                .build();
        surveyList.add(survey);


        //stub
        when(surveyRepository.findByPosterPosterId(poster.getPosterId())).thenReturn(surveyList);

        //when
        SurveyResponseDto.SurveyList SurveyResponseDto = surveyService.findPost(poster.getPosterId());

        //then
        assertThat(SurveyResponseDto.getSurveyList().size()).isEqualTo(1);
        assertThat(SurveyResponseDto.getSurveyList().get(0).getChoice()).isEqualTo("선택지");
        assertThat(SurveyResponseDto.getSurveyList().get(0).getChoiceResult()).isEqualTo("결과");
    }

}