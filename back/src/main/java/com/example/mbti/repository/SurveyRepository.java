package com.example.mbti.repository;

import com.example.mbti.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository <Survey, Long> {
}
