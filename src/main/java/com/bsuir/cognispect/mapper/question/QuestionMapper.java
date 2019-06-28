package com.bsuir.cognispect.mapper.question;

import com.bsuir.cognispect.entity.*;
import com.bsuir.cognispect.mapper.answer.ChooseAnswerMapper;
import com.bsuir.cognispect.mapper.answer.MatchAnswerMapper;
import com.bsuir.cognispect.mapper.answer.SortAnswerMapper;
import com.bsuir.cognispect.model.question.QuestionModel;
import com.bsuir.cognispect.repository.ChooseAnswerRepository;
import com.bsuir.cognispect.repository.MatchAnswerRepository;
import com.bsuir.cognispect.repository.SortAnswerRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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
    @Autowired
    private ChooseAnswerRepository chooseAnswerRepository;
    @Autowired
    private SortAnswerRepository sortAnswerRepository;
    @Autowired
    private MatchAnswerRepository matchAnswerRepository;

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
                List<ChooseAnswer> chooseAnswers = new ArrayList<>();

                for (Answer answer : question.getAnswers()) {
                    chooseAnswers.add(chooseAnswerRepository.findById(answer.getId()).get());
                }
                questionModel.setChooseAnswers(chooseAnswerMapper.entitiesToModels(chooseAnswers));
                break;
            case SORT:
                List<SortAnswer> sortAnswers = new ArrayList<>();

                for (Answer answer : question.getAnswers()) {
                    sortAnswers.add(sortAnswerRepository.findById(answer.getId()).get());
                }
                questionModel.setSortAnswers(sortAnswerMapper.entitiesToModels(sortAnswers));
                break;
            case MATCH:
                List<MatchAnswer> matchAnswers = new ArrayList<>();

                for (Answer answer : question.getAnswers()) {
                    matchAnswers.add(matchAnswerRepository.findById(answer.getId()).get());
                }
                questionModel.setMatchAnswers(matchAnswerMapper.entitiesToModels(matchAnswers));
                break;
        }
        return questionModel;
    }

    public abstract List<QuestionModel> entitiesToModels(
            Collection<Question> questions);
}
