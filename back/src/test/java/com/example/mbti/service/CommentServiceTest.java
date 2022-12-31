package com.example.mbti.service;

import com.example.mbti.dto.request.CommentRequestDto;
import com.example.mbti.dto.response.CommentResponseDto;
import com.example.mbti.model.Comment;
import com.example.mbti.model.Poster;
import com.example.mbti.repository.CommentRepository;
import com.example.mbti.repository.Poster.PosterRepository;
import com.example.mbti.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PosterRepository posterRepository;

    @Test
    void 댓글등록() {
        //given
        String posterTitle = "심리테스트 유형 제목";
        String passage = "지문";
        String imgUrl = "심리테스트 이미지주소";

        Poster poster = Poster.builder()
                .posterId(1L)
                .posterTitle(posterTitle)
                .imgUrl(imgUrl)
                .passage(passage)
                .build();

        CommentRequestDto dto = new CommentRequestDto();
        dto.setComment("댓글내용1");

        Comment comment = Comment.builder()
                .comment(dto.getComment())
                .poster(poster)
                .build();

        //stub
        when(commentRepository.save(any())).thenReturn(comment);
        when(posterRepository.findById(poster.getPosterId())).thenReturn(Optional.of(poster));

        //when
        CommentResponseDto commentResponseDto = commentService.addComment(poster.getPosterId(), dto);

        //then
        assertThat(commentResponseDto.getPosterId()).isEqualTo(1);
        assertThat(commentResponseDto.getComment()).isEqualTo(dto.getComment());
    }

    @Test
    @DisplayName("댓글조회")
    void 댓글조회() {
        //given
        String posterTitle = "심리테스트 유형 제목";
        String passage = "지문";
        String imgUrl = "심리테스트 이미지주소";

        Poster poster = Poster.builder()
                .posterId(1L)
                .posterTitle(posterTitle)
                .imgUrl(imgUrl)
                .passage(passage)
                .build();

        List<Comment> commentList = new ArrayList<>();
        for(int i=0; i<5; i++){
            Comment comment = Comment.builder()
                    .comment("댓글"+i)
                    .poster(poster)
                    .build();
            commentList.add(comment);
        }

        //stub
        when(commentRepository.findByPosterPosterId(poster.getPosterId())).thenReturn(commentList);

        //when
        CommentResponseDto.CommentList responseCommentList = commentService.findComment(poster.getPosterId());

        //then
        assertThat(responseCommentList.getCommentList().size()).isEqualTo(5);
        assertThat(responseCommentList.getCommentList().get(0).getPosterId()).isEqualTo(1);
        assertThat(responseCommentList.getCommentList().get(0).getComment()).isEqualTo("댓글0");
        assertThat(responseCommentList.getCommentList().get(1).getComment()).isEqualTo("댓글1");
    }
}