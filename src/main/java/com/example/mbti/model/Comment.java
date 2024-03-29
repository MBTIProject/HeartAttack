package com.example.mbti.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private Long commentId;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posterId")
    private Poster poster;

    @Builder
    public Comment(String comment, Poster poster){
        this.comment = comment;
        this.poster = poster;
    }
}