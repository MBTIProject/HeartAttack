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
    private Long posterId;

    private String comment;

    private LocalDateTime date;

    public CommentResponseDto(Comment comment) {
        this.posterId = comment.getPoster().getPosterId();
        this.comment = comment.getComment();
        this.date = comment.getModifiedAt();
    }
}
