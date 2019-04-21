package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.QuestionDto;
import com.bsuir.cognispect.entity.Question;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring",
        uses = {TopicMapper.class,
                AnswerMapper.class,
                MatchAnswerMapper.class,
                SortAnswerMapper.class,
                SubstitutionMapper.class})
public abstract class QuestionMapper {

    public abstract QuestionDto questionToQuestionDto(Question question);

   /* public Question questionDtoToQuestion(QuestionDto questionDto) {
        return Question.builder()
                .id(UUID.randomUUID())
                .type(questionDto.getType())
                *//*  .topic(topicService
                          .getTopicByName(questionDto.getTopic()))*//*
                .description(questionDto.getDescription())
                .answers(new ArrayList<>(answerMapper
                        .answersDtoToAnswers(questionDto.getAnswers())))
                .build();
    }*/

    public abstract List<QuestionDto> questionsToQuestionsDto(
            Collection<Question> questions);
}
