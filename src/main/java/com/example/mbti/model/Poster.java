package com.example.mbti.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Poster extends Timestamped  {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posterId")
    private Long posterId;

    @Column(nullable = false)
    private String posterTitle;

    @Column(nullable = false)
    private String imgUrl;

    @Column(columnDefinition = "integer default 0")
    private int posterViewCount;

    @Column(nullable = false)
    private String passage;

    @OneToMany(mappedBy = "poster")
    private List<Survey> surveyList = new ArrayList<>();

    @Builder
    public Poster(Long posterId, String posterTitle, String imgUrl, String passage){
        this.posterId = posterId;
        this.posterTitle = posterTitle;
        this.imgUrl = imgUrl;
        this.passage = passage;
    }

    public void updatePostCnt() {
        this.posterViewCount +=1;
    }
}
