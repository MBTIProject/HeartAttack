package com.example.mbti.repository;

import com.example.mbti.model.Comment;
import com.example.mbti.model.Poster;
import com.example.mbti.repository.Poster.PosterRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PosterRepository posterRepository;

    @BeforeEach
    void 포스터_데이터준비(){
        String posterTitle = "심리테스트 유형 제목1";
        String imgUrl = "심리테스트 유형 주소1";
        String passage = "심리테스트 유형 지문";
        Poster poster = Poster.builder()
                .posterTitle(posterTitle)
                .imgUrl(imgUrl)
                .passage(passage)
                .build();
        posterRepository.save(poster);
    }

    @Test
    void 댓글등록(){
        //given
        String comment = "댓글내용";
        Optional<Poster> optPoster = posterRepository.findByTitle("심리테스트 유형 제목1");

        Comment commentEntity = Comment.builder()
                .comment(comment)
                .poster(optPoster.get())
                .build();

        //when
        Comment saveComment = commentRepository.save(commentEntity);

        //then
        Assertions.assertThat(saveComment.getPoster().getPosterId()).isEqualTo(optPoster.get().getPosterId());

    }

}