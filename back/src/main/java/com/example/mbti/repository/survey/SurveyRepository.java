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
    @Query("select distinct s from Survey s left join fetch s.poster where s.poster.posterId=:poster_id")
    List<Survey> findByPoster_id(@Param("poster_id") Long poster_id);

    @Query("select s from Survey s left join fetch s.poster where s.poster.posterId = :posterId and s.surveyId = :surveyId")
    Optional<Survey> findByPoster_idAndSurvey_id(@Param("posterId") Long posterId, @Param("surveyId") Long surveyId);
}
