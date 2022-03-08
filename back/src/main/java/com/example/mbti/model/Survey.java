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
    private String choice;

    @Column(nullable = false)
    private String choice_result;

    @Column(columnDefinition = "integer default 0")
    private int choice_view_count;

    @Builder
    public Survey (String choice, String choice_result, Poster poster){
        this.choice = choice;
        this.choice_result = choice_result;
        this.poster = poster;
    }

    public void updateSelectionCnt() {
        this.choice_view_count +=1;
    }
}