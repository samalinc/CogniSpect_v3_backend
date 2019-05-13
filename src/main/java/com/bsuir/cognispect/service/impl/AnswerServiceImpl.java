package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.dto.*;
import com.bsuir.cognispect.entity.*;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.mapper.AnswerMapper;
import com.bsuir.cognispect.mapper.MatchAnswerMapper;
import com.bsuir.cognispect.mapper.SortAnswerMapper;
import com.bsuir.cognispect.repository.*;
import com.bsuir.cognispect.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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
    @Autowired
    private AnswerVariantRepository answerVariantRepository;
    @Autowired
    private SortAnswerVariantRepository sortAnswerVariantRepository;
    @Autowired
    private MatchAnswerVariantRepository matchAnswerVariantRepository;

    @Override
    public List<Answer> createAnswersWithQuestion(
            List<AnswerDto> answersDto,
            Question question) {
        if (answersDto == null || question == null) {
            return null;
        }

        List<Answer> answerList = answerMapper.modelsToEntities(answersDto);
        answerList.forEach(answer -> answer.setQuestion(question));

        return answerList;
    }

    @Override
    public List<Answer> createSubstitutionAnswersWithQuestion(
            List<AnswerDto> answersDto,
            List<SubstitutionDto> substitutionsDto,
            Question question) {
        List<Answer> answerList = answerMapper.modelsToEntities(answersDto);
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
                .modelsToEntities(matchAnswersDto);
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
                .modelsToEntities(sortAnswersDto);
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
    public List<Answer> updateAnswersInQuestion(List<AnswerDto> answersDto) {
        List<Answer> answers = new ArrayList<>();

        for (AnswerDto answerDto : answersDto) {

        }

        return answers;
    }

    @Override
    public Answer updateAnswer(AnswerDto answerDto) {
        Answer answer = answerRepository.findById(answerDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Answer", answerDto.getId()));
        answer.setCorrect(answerDto.isCorrect());
        answer.setText(answerDto.getText());

        return answerRepository.save(answer);
    }

    @Override
    public Answer createAnswer(AnswerDto answerDto, Question question) {
        if (answerDto == null || question == null) {
            return null;
        }

        Answer answer = answerMapper.modelToEntity(answerDto);
        answer.setQuestion(question);

        return answerRepository.save(answer);
    }

    @Override
    public void submitAnswers(UserAnswersDto userAnswersDto) {
        List<ChooseAnswerVariant> chooseAnswerVariants = submitChooseAnswers(userAnswersDto.getAnswersIds());
    }

    private List<ChooseAnswerVariant> submitChooseAnswers(List<UUID> answersIds) {
        List<ChooseAnswerVariant> userAnswers = new ArrayList<>();

        for (UUID answerId : answersIds) {
            AnswerVariant answerVariant = answerVariantRepository.findById(answerId)
                    .orElseThrow(() -> new ResourceNotFoundException("ChooseAnswerVariant", answerId));
            /*chooseAnswerVariant.setStudentChose(true);

            userAnswers.add(answerVariantRepository.save(chooseAnswerVariant));*/
        }

        return userAnswers;
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

    @Override
    public Answer deleteAnswer(UUID answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(
                () -> new ResourceNotFoundException("Answer", answerId));
        answerRepository.delete(answer);

        return answer;
    }
}
