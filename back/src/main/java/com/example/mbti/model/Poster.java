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
    private String img_url;

    @Column(columnDefinition = "integer default 0")
    private int poster_view_count;

    @Builder
    public Poster(String poster_title, String img_url){
        this.poster_title = poster_title;
        this.img_url = img_url;
    }

    public void updatePostCnt() {
        this.poster_view_count +=1;
    }
}
