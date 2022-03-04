package com.example.mbti.repository;

import com.example.mbti.model.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PosterRepository extends JpaRepository<Poster, Long> {
    int updatePoster(Long poster_id);

    Optional<Poster> findByTitle(String poster_title);
}