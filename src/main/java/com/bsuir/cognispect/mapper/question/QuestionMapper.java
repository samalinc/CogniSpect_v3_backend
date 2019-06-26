package com.bsuir.cognispect.mapper.question;

import com.bsuir.cognispect.entity.ChooseAnswer;
import com.bsuir.cognispect.entity.MatchAnswer;
import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.SortAnswer;
import com.bsuir.cognispect.mapper.answer.ChooseAnswerMapper;
import com.bsuir.cognispect.mapper.answer.MatchAnswerMapper;
import com.bsuir.cognispect.mapper.answer.SortAnswerMapper;
import com.bsuir.cognispect.model.question.QuestionModel;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class QuestionMapper {
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private SubstitutionMapper substitutionMapper;
    @Autowired
    private ChooseAnswerMapper chooseAnswerMapper;
    @Autowired
    private SortAnswerMapper sortAnswerMapper;
    @Autowired
    private MatchAnswerMapper matchAnswerMapper;

    public QuestionModel entityToModel(Question question) {
        if (question == null) {
            return null;
        }

        QuestionModel questionModel = new QuestionModel();

        questionModel.setId(question.getId());
        questionModel.setDescription(question.getDescription());
        questionModel.setType(question.getType());
        questionModel.setSubstitutions(substitutionMapper.entitiesToModels(question.getSubstitutions()));
        questionModel.setTopic(topicMapper.entityToModel(question.getTopic()));
        switch (question.getType()) {
            case CHOOSE:
            case MULTICHOOSE:
            case SUBSTITUTION:
                questionModel.setChooseAnswers(chooseAnswerMapper.entitiesToModels(
                        (List<ChooseAnswer>) (List<?>) question.getAnswers()));
                break;
            case SORT:
                questionModel.setSortAnswers(sortAnswerMapper.entitiesToModels(
                        (List<SortAnswer>) (List<?>) question.getAnswers()));
                break;
            case MATCH:
                questionModel.setMatchAnswers(matchAnswerMapper.entitiesToModels(
                        (List<MatchAnswer>) (List<?>) question.getAnswers()));
                break;
        }
        return questionModel;
    }

    public abstract List<QuestionModel> entitiesToModels(
            Collection<Question> questions);
}
