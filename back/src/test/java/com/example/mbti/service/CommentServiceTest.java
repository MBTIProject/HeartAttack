package com.example.mbti.service;

import com.example.mbti.dto.request.CommentRequestDto;
import com.example.mbti.model.Comment;
import com.example.mbti.model.Poster;
import com.example.mbti.repository.CommentRepository;
import com.example.mbti.repository.Poster.PosterRepository;
import com.example.mbti.service.impl.CommentServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
        String addComment = commentService.addComment(poster.getPosterId(), dto);

        //then
        Assertions.assertThat(addComment).isEqualTo(dto.getComment());
    }
}