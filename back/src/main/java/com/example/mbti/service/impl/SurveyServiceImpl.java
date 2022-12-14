package com.example.mbti.service.impl;

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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    private final PosterRepository posterRepository;

    @Override
    @Transactional
    public int update(Long posterId,Long surveyId){
        Optional<Survey> byPoster_idAndSurvey_id = surveyRepository.findByPoster_idAndSurvey_id(posterId, surveyId);
        byPoster_idAndSurvey_id.get().updateSelectionCnt();
        return byPoster_idAndSurvey_id.get().getChoiceViewCount();
    }

    @Override
    @Transactional
    public void addSurvey(SurveyRequestDto surveyRequestDto, Long posterId) {
        Optional<Poster> findPosterId = posterRepository.findById(posterId);
        Survey survey = Survey.builder()
                .poster(findPosterId.get())
                .choice(surveyRequestDto.getChoice())
                .choiceResult(surveyRequestDto.getChoiceResult())
                .build();
        surveyRepository.save(survey);
    }

    @Override
    public List<SurveyResponseDto> findPost(Long posterId) {
        return surveyRepository.findByPoster_id(posterId).stream()
                .map(SurveyResponseDto::new)
                .collect(Collectors.toList());
    }
}
