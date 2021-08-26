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
@SequenceGenerator(
        name = "SURVEY",
        sequenceName = "SURVEY_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
@NamedQuery(name="Survey.findByPoster_id", query="select s from Survey s where s.poster_id=:poster_id")
public class Survey {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SURVEY")
    @Id
    private Long id;

    @Column(nullable = false)
    private Long poster_id;

    @Column(nullable = false)
    private int selection_id;   //추가

    @Column(nullable = false)
    private String selection;

    @Column(nullable = false)
    private String answer_title;  //추가

    @Column(columnDefinition = "TEXT",nullable = false)
    private String answer;

    @Column(nullable = false)
    private int answer_cnt = 0;

    public Survey(SurveyRequestDto surveyRequestDto) {
        this.selection_id = surveyRequestDto.getSelection_id();
        this.selection = surveyRequestDto.getSelection();
        this.answer = surveyRequestDto.getAnswer();
        this.answer_title = surveyRequestDto.getAnswer_title();
        this.poster_id=surveyRequestDto.getPoster_id();
    }
}