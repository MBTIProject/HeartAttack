package com.example.mbti.repository.Poster;

import com.example.mbti.dto.response.PosterResponseDto;
import com.example.mbti.model.Poster;
import com.example.mbti.model.Survey;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.mbti.model.QPoster.*;
import static com.example.mbti.model.QSurvey.*;

@RequiredArgsConstructor
public class PosterRepositoryImpl implements PosterRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public PosterResponseDto findPosterList() {
        List<Tuple> result = jpaQueryFactory
                .select(poster, survey)
                .from(poster)
                .leftJoin(survey).on(poster.id.eq(survey.poster.id)).fetchJoin()
                .fetch();

        List<Survey> surveyList = result.stream().
                map(r -> r.get(survey))
                .collect(Collectors.toList());

        Poster Poster = result.stream()
                .map(r -> r.get(poster))
                .findFirst()
                .get();

        return PosterResponseDto.of(surveyList,Poster);
    }
}
