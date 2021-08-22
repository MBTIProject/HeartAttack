package com.example.mbti.model;

import com.example.mbti.dto.SurveyRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Setter
public class Survey {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long poster_id;

    @Column(nullable = false)
    private String quiz;

    @Column(nullable = false)
    private String selection;

    @Column(nullable = false)
    private String answer;

    @Column(nullable = false)
    private int answer_cnt = 0;

    public Survey(SurveyRequestDto surveyRequestDto) {
        this.quiz = surveyRequestDto.getQuiz();
        this.selection = surveyRequestDto.getSelection();
        this.answer = surveyRequestDto.getAnswer();
        this.poster_id=surveyRequestDto.getPoster_id();
    }
}