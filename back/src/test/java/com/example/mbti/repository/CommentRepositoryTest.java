package com.example.mbti.repository;

import com.example.mbti.model.Comment;
import com.example.mbti.model.Poster;
import com.example.mbti.repository.Poster.PosterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
        Optional<Poster> optPoster = posterRepository.findByPosterTitle("심리테스트 유형 제목1");

        Comment commentEntity = Comment.builder()
                .comment(comment)
                .poster(optPoster.get())
                .build();

        //when
        Comment saveComment = commentRepository.save(commentEntity);

        //then
        assertThat(saveComment.getPoster().getPosterId()).isEqualTo(optPoster.get().getPosterId());
        assertThat(saveComment.getComment()).isEqualTo(commentEntity.getComment());
    }

    @Test
    void 심리테스트로댓글조회(){
        //given
        String comment = "댓글내용";
        Optional<Poster> optPoster = posterRepository.findByPosterTitle("심리테스트 유형 제목1");

        Comment commentEntity = Comment.builder()
                .comment(comment)
                .poster(optPoster.get())
                .build();
        Comment saveComment = commentRepository.save(commentEntity);

        //when
        List<Comment> commentList = commentRepository.findByPosterPosterId(optPoster.get().getPosterId());

        //then
        assertThat(commentList.size()).isEqualTo(1);
        assertThat(commentList.get(0).getCommentId()).isEqualTo(saveComment.getCommentId());
        assertThat(commentList.get(0).getComment()).isEqualTo(saveComment.getComment());
    }

}