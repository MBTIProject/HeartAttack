package com.example.mbti.repository;

import com.example.mbti.model.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PosterRepository extends JpaRepository<Poster, Long> {
    @Modifying
    @Query("update Poster set poster_cnt = poster_cnt+1 where poster_id=:poster_id")
    int updatePoster(Long poster_id);
}