package com.example.mbti.service.impl;

import com.example.mbti.advice.exception.ApiRequestException;
import com.example.mbti.dto.request.SurveyRequestDto;
import com.example.mbti.dto.response.SurveyResponseDto;
import com.example.mbti.model.Poster;
import com.example.mbti.model.Survey;
import com.example.mbti.repository.Poster.PosterRepository;
import com.example.mbti.repository.survey.SurveyRepository;
import com.example.mbti.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.mbti.advice.ResultInfo.makeResultMap;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    private final PosterRepository posterRepository;

    @Override
    @Transactional
    public
    SurveyResponseDto update(Long surveyId){
        Optional<Survey> optSurvey = Optional.ofNullable(surveyRepository.findById(surveyId).orElseThrow(() -> {
            throw new ApiRequestException("존재하지 않는 심리테스트 선택지, 선택지결과입니다.");
        }));
        optSurvey.get().updateSelectionCnt();
        return new SurveyResponseDto(optSurvey.get());
    }

    @Override
    @Transactional
    public SurveyResponseDto addSurvey(SurveyRequestDto surveyRequestDto, Long posterId) {
        Optional<Poster> optPoster = Optional.ofNullable(posterRepository.findById(posterId).orElseThrow(() -> {
            throw new ApiRequestException("존재하지 않는 심리테스트 유형입니다.");
        }));
        return new SurveyResponseDto(surveyRepository.save(surveyRequestDto.toEntity(optPoster.get())));
    }

    @Override
    public SurveyResponseDto.SurveyList findPost(Long posterId) {
        List<SurveyResponseDto> surveyList = surveyRepository.findByPosterPosterId(posterId).stream()
                .map(SurveyResponseDto::new)
                .collect(Collectors.toList());
        return new SurveyResponseDto.SurveyList(surveyList);
    }
}
