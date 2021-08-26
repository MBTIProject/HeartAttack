package com.example.mbti.service;

import com.example.mbti.repository.SurveyRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Getter
@RequiredArgsConstructor
@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;

    @Transactional
    public Long update(Long id){
        surveyRepository.updateSurvey(id);
        return id;
    }
}
