package com.example.mbti.model;

import com.example.mbti.dto.PosterRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@SequenceGenerator(
        name = "POSTER",
        sequenceName = "MEMBER_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
public class Poster {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTER")
    @Id
    private Long poster_id;

    @Column(nullable = false)
    private String poster_title;

    @Column(nullable = false)
    private String img_url;

    @Column(nullable = false)
    private int poster_cnt = 0;

    public Poster(PosterRequestDto posterRequestDto) {
        this.poster_title = posterRequestDto.getPoster_title();
        this.img_url = posterRequestDto.getImg_url();
    }
}
