package com.example.mbti.model;

import com.example.mbti.dto.CommentRequestDto;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Poster poster;

    @Builder
    public Comment(String comment, Poster poster){
        this.comment = comment;
        this.poster = poster;
    }
}