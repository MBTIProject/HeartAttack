package com.example.mbti.dto.request;

import com.example.mbti.model.Comment;
import com.example.mbti.model.Poster;
import lombok.*;

@Getter
@NoArgsConstructor
@Setter
public class CommentRequestDto {
    private String comment;

    public Comment toEntity(Poster poster){
        return Comment.builder()
                .comment(comment)
                .poster(poster)
                .build();
    }
}