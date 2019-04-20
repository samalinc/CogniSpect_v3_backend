package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.QuestionDto;
import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.service.SubjectService;
import com.bsuir.cognispect.service.TopicService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Mapper(componentModel = "spring")
public abstract class QuestionMapper {
    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private MatchAnswerMapper matchAnswerMapper;

    @Autowired
    private SortAnswerMapper sortAnswerMapper;

    @Autowired
    private TopicService topicService;

    public QuestionDto questionToQuestionDto(Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .type(question.getType())
                .subject(question.getTopic().getSubject().getName())
                .topic(question.getTopic().getName())
                .description(question.getDescription())
                .answers(new ArrayList<>(answerMapper.answersToAnswersDto(
                        question.getAnswers())))
                .build();
    }

    public Question questionDtoToQuestion(QuestionDto questionDto) {
        return Question.builder()
                .id(UUID.randomUUID())
                .type(questionDto.getType())
                .topic(topicService
                        .getTopicByName(questionDto.getTopic()))
                .description(questionDto.getDescription())
                .answers(new ArrayList<>(answerMapper
                        .answersDtoToAnswers(questionDto.getAnswers())))
                .build();
    }

    public abstract List<QuestionDto> questionsToQuestionsDto(
            Collection<Question> questions);
}
