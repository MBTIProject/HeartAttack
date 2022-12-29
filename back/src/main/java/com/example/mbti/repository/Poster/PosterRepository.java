package com.example.mbti.repository.Poster;

import com.example.mbti.model.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PosterRepository extends JpaRepository<Poster, Long>, PosterRepositoryCustom {

    Optional<Poster> findByPosterTitle(String posterTitle);

    @Query("select distinct p from Poster p left join fetch p.surveyList")
    List<Poster> findAllPosterAndSurvey();
}