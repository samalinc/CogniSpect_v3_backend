package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.QuestionDto;
import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.exception.QuestionTypeNotFoundException;
import com.bsuir.cognispect.repository.QuestionTypeRepository;
import com.bsuir.cognispect.service.SubjectService;
import com.bsuir.cognispect.service.TopicService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


@Mapper(componentModel = "spring")
public abstract class QuestionMapper {
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private TopicService topicService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    public QuestionDto questionToQuestionDto(Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .type(question.getType().getQuestionType())
                .subject(question.getSubject().getName())
                .topic(question.getTopic().getName())
                .description(question.getDescription())
                .answers(new ArrayList<>(answerMapper.answersToAnswersDto(
                        question.getAnswers())))
                .build();
    }

    public Question questionDtoToQuestion(QuestionDto questionDto) {
        return Question.builder()
                .id(UUID.randomUUID())
                .type(questionTypeRepository
                        .findQuestionTypeByQuestionType(questionDto.getType())
                        .orElseThrow(() -> new QuestionTypeNotFoundException(
                                "Question type: "
                                        + questionDto.getType() + "not found"
                        ))
                )
                .subject(subjectService
                        .getSubjectByName(questionDto.getSubject()))
                .topic(topicService
                        .getTopicByName(questionDto.getTopic()))
                .description(questionDto.getDescription())
                .answers(new HashSet<>(answerMapper
                        .answersDtoToAnswers(questionDto.getAnswers())))
                .build();
    }

    public abstract List<QuestionDto> questionsToQuestionsDto(
            Collection<Question> questions);
}
