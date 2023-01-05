package com.example.mbti.repository.survey;

import com.example.mbti.model.Survey;
import com.example.mbti.repository.Poster.PosterRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SurveyRepository extends JpaRepository <Survey, Long>, SurveyRepositoryCustom {
    List<Survey> findByPosterPosterId(Long poster_id);
}
