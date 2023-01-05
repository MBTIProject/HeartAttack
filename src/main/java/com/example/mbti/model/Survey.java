package com.example.mbti.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Survey extends Timestamped  {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "surveyId")
    private Long surveyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posterId")
    private Poster poster;

    @Column(nullable = false)
    private String choice;

    @Column(nullable = false)
    private String choiceResult;

    @Column(columnDefinition = "integer default 0")
    private int choiceViewCount;

    @Builder
    public Survey (String choice, String choiceResult, Poster poster){
        this.choice = choice;
        this.choiceResult = choiceResult;
        this.poster = poster;
    }

    public void updateSelectionCnt() {
        this.choiceViewCount +=1;
    }
}