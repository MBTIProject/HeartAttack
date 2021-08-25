package com.example.mbti.repository;

import com.example.mbti.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyRepository extends JpaRepository <Survey, Long> {
    List<Survey> findByPoster_id(Long poster_id);
    @Modifying
    @Query("update Survey set answer_cnt = answer_cnt+1 where id=:id")
    int updateSurvey(Long id);
}
