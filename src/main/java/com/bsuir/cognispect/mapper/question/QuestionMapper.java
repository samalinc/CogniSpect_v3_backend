package com.bsuir.cognispect.mapper.question;

import com.bsuir.cognispect.mapper.answer.AnswerMapper;
import com.bsuir.cognispect.mapper.answer.MatchAnswerMapper;
import com.bsuir.cognispect.mapper.answer.SortAnswerMapper;
import com.bsuir.cognispect.model.question.QuestionModel;
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

    public abstract QuestionModel entityToModel(Question question);

    public abstract List<QuestionModel> entitiesToModels(
            Collection<Question> questions);
}
