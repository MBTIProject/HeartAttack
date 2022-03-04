package com.example.mbti.repository;

import com.example.mbti.model.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PosterRepository extends JpaRepository<Poster, Long> {

    @Query("SELECT p from Poster p where p.poster_title =:poster_title")
    Optional<Poster> findByTitle(@Param("poster_title") String poster_title);
}