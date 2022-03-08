package com.example.mbti.dto.response;

import com.example.mbti.model.Comment;
import com.example.mbti.model.Survey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    private Long poster_id;

    private String comment;

    private LocalDateTime date;

    public CommentResponseDto(Comment comment) {
        this.poster_id = comment.getPoster().getId();
        this.comment = comment.getComment();
        this.date = comment.getModifiedAt();
    }
}
