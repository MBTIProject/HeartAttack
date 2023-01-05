package com.example.mbti.dto.response;

import com.example.mbti.model.Comment;
import com.example.mbti.model.Survey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    private Long posterId;

    private String comment;

    private LocalDateTime createDate;

    public CommentResponseDto(Comment comment) {
        this.posterId = comment.getPoster().getPosterId();
        this.comment = comment.getComment();
        this.createDate = comment.getModifiedAt();
    }

    @Getter
    public static class CommentList{
        private List<CommentResponseDto> commentList = new ArrayList<>();

        public CommentList(List<CommentResponseDto> commentList) {
            this.commentList = commentList;
        }
    }
}
