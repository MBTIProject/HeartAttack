package com.example.mbti.model;

import com.example.mbti.dto.SurveyRequestDto;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Survey extends Timestamped  {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Poster poster;

    @Column(nullable = false)
    private String passage;

    @Builder
    public Survey (String passage, Poster poster){
        this.passage = passage;
        this.poster = poster;
    }
}