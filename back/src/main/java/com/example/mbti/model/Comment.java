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
@NamedQuery(name ="Comment.findByPoster_id", query="select c from Comment c where c.poster_id=:poster_id")
public class Comment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private  String name;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Long poster_id;

    public Comment(CommentRequestDto commentRequestDto){
        this.name = commentRequestDto.getName();
        this.comment = commentRequestDto.getComment();
        this.poster_id = commentRequestDto.getPoster_id();
    }
}
