package com.example.mbti.model;

import com.example.mbti.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Setter
@SequenceGenerator(
        name = "COMMENT",
        sequenceName = "COMMENT_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
@NamedQuery(name ="Comment.findByPoster_id", query="select c from Comment c where c.poster_id=:poster_id")
public class Comment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT")
    @Id
    private Long id;



    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Long poster_id;

    public Comment(CommentRequestDto commentRequestDto){

        this.comment = commentRequestDto.getComment();
        this.poster_id = commentRequestDto.getPoster_id();
    }
}