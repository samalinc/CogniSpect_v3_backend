package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.dto.AnswerDto;
import com.bsuir.cognispect.dto.MatchAnswerDto;
import com.bsuir.cognispect.dto.SortAnswerDto;
import com.bsuir.cognispect.dto.SubstitutionDto;
import com.bsuir.cognispect.entity.*;
import com.bsuir.cognispect.mapper.AnswerMapper;
import com.bsuir.cognispect.mapper.MatchAnswerMapper;
import com.bsuir.cognispect.mapper.SortAnswerMapper;
import com.bsuir.cognispect.repository.AnswerRepository;
import com.bsuir.cognispect.repository.MatchAnswerRepository;
import com.bsuir.cognispect.repository.SortAnswerRepository;
import com.bsuir.cognispect.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private MatchAnswerMapper matchAnswerMapper;
    @Autowired
    private SortAnswerMapper sortAnswerMapper;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private MatchAnswerRepository matchAnswerRepository;
    @Autowired
    private SortAnswerRepository sortAnswerRepository;

    @Override
    public List<Answer> createAnswersWithQuestion(
            List<AnswerDto> answersDto,
            Question question) {
        if (answersDto == null || question == null) {
            return null;
        }

        List<Answer> answerList = answerMapper.answersDtoToAnswers(answersDto);
        answerList.forEach(answer -> answer.setQuestion(question));

        return answerList;
    }

    @Override
    public List<Answer> createSubstitutionAnswersWithQuestion(
            List<AnswerDto> answersDto,
            List<SubstitutionDto> substitutionsDto,
            Question question) {
        List<Answer> answerList = answerMapper.answersDtoToAnswers(answersDto);
        answerList.forEach(answer -> answer.setQuestion(question));

        question.setSubstitutions(
                createSubstitutions(substitutionsDto, answerList, question));

        return answerList;
    }

    @Override
    public List<MatchAnswer> createMatchAnswerWithQuestion(
            List<MatchAnswerDto> matchAnswersDto, Question question) {
        if (matchAnswersDto == null || question == null) {
            return null;
        }

        List<MatchAnswer> matchAnswerList = matchAnswerMapper
                .matchAnswersDtoToMatchAnswers(matchAnswersDto);
        matchAnswerList.forEach(
                matchAnswer -> matchAnswer.setQuestion(question));

        return matchAnswerList;
    }

    @Override
    public List<SortAnswer> createSortAnswerWithQuestion(
            List<SortAnswerDto> sortAnswersDto, Question question) {
        if (sortAnswersDto == null || question == null) {
            return null;
        }

        List<SortAnswer> sortAnswerList = sortAnswerMapper
                .sortAnswersDtoToSortAnswers(sortAnswersDto);
        sortAnswerList.forEach(
                sortAnswer -> sortAnswer.setQuestion(question));

        return sortAnswerList;
    }

    @Override
    public List<Substitution> createSubstitutions(
            List<SubstitutionDto> substitutionsDto,
            List<Answer> answers,
            Question question) {
        if (substitutionsDto == null || answers == null || question == null) {
            return null;
        }

        List<Substitution> substitutionList = new ArrayList<>();

        for (SubstitutionDto substitutionDto : substitutionsDto) {
            Substitution substitution = new Substitution();
            substitution.setText(substitutionDto.getText());
            substitution.setQuestion(question);
            substitution.setRightAnswer(answers.stream()
                    .filter(answer -> answer.getText().equals(
                            substitutionDto.getRightAnswer().getText()))
                    .findAny().orElseThrow(RuntimeException::new));
            substitutionList.add(substitution);
        }

        return substitutionList;
    }

    @Override
    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public MatchAnswer saveMatchAnswer(MatchAnswer matchAnswer) {
        return matchAnswerRepository.save(matchAnswer);
    }

    @Override
    public SortAnswer saveSortAnswer(SortAnswer sortAnswer) {
        return sortAnswerRepository.save(sortAnswer);
    }

    @Override
    public List<Answer> saveAnswers(List<Answer> answers) {
        return answerRepository.saveAll(answers);
    }

    @Override
    public List<MatchAnswer> saveMatchAnswers(List<MatchAnswer> matchAnswers) {
        return matchAnswerRepository.saveAll(matchAnswers);
    }

    @Override
    public List<SortAnswer> saveSortAnswers(List<SortAnswer> sortAnswers) {
        return sortAnswerRepository.saveAll(sortAnswers);
    }
}
