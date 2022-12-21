package com.example.mbti.service;

import com.example.mbti.dto.request.SurveyRequestDto;
import com.example.mbti.dto.response.SurveyResponseDto;
import com.example.mbti.model.Poster;
import com.example.mbti.model.Survey;
import com.example.mbti.repository.Poster.PosterRepository;
import com.example.mbti.repository.survey.SurveyRepository;
import com.example.mbti.service.impl.SurveyServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

}