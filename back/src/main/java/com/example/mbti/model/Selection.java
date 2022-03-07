package com.example.mbti.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Selection extends Timestamped  {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String choice;

    @Column(nullable = false)
    private String choice_result;

    @Column(columnDefinition = "integer default 0")
    private int choice_view_count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Builder
    public Selection(String choice, String choice_result){
        this.choice = choice;
        this.choice_result = choice_result;
    }

    public void updateSelectionCnt() {
        this.choice_view_count +=1;
    }
}
