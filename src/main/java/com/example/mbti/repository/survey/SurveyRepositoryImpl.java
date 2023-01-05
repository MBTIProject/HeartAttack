package com.example.mbti.repository.survey;

public class SurveyRepositoryImpl implements SurveyRepositoryCustom{

//    private JPAQueryFactory jpaQueryFactory;
//    @Override
//    public List<Survey> findByPoster_id(Long poster_id) {
//        List<Tuple> result = jpaQueryFactory
//                .select(survey, poster)
//                .from(survey)
//                .leftJoin(poster).on(survey.poster.id.eq(poster.id)).fetchJoin()
//                .fetch();
//
//        List<Poster> posterList = result.stream()
//                .map(r -> r.get(poster))
//                .collect(Collectors.toList());
//
//        Survey survey = result.stream()
//                .map(r -> r.get(QSurvey.survey))
//                .findFirst()
//                .get();
//
//        return SurveyResponseDto.of(posterList,survey);
//    }
}
