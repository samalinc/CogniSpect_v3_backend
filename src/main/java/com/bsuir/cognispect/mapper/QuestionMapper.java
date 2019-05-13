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

    public abstract QuestionDto entityToModel(Question question);

    public abstract List<QuestionDto> entitiesToModels(
            Collection<Question> questions);
}
