package com.example.mbti.repository;

import com.example.mbti.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where c.poster.id=:posterId")
    List<Comment> findByPosterId(@Param("posterId") Long posterId);
    //List<Comment> findByPoster_id(Long poster_id);
}