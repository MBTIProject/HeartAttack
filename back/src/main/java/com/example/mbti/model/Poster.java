package com.example.mbti.model;

import com.example.mbti.dto.PosterRequestDto;
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
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String poster_title;

    @Column(nullable = false)
    private String quiz;

    @Column(nullable = false)
    private String img_url;

    @Column(columnDefinition = "integer default 0")
    private int view_count;

    @OneToMany(mappedBy = "poster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    public Poster(PosterRequestDto posterRequestDto) {
        this.poster_title = posterRequestDto.getPoster_title();
        this.quiz = posterRequestDto.getQuiz();
        this.img_url = posterRequestDto.getImg_url();
    }

    @Builder
    public Poster(String poster_title, String quiz, String img_url){
        this.poster_title = poster_title;
        this.quiz = quiz;
        this.img_url = img_url;
    }

    public void updatePostCnt() {
        this.view_count +=1;
    }
}
