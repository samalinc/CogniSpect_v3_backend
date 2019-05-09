package com.bsuir.cognispect.service;

import com.bsuir.cognispect.dto.AnswerDto;
import com.bsuir.cognispect.dto.MatchAnswerDto;
import com.bsuir.cognispect.dto.SortAnswerDto;
import com.bsuir.cognispect.dto.SubstitutionDto;
import com.bsuir.cognispect.entity.*;

import java.util.List;
import java.util.UUID;


public interface AnswerService {
    List<Answer> createAnswersWithQuestion(
            List<AnswerDto> answersDto,
            Question question);

    List<Answer> createSubstitutionAnswersWithQuestion(
            List<AnswerDto> answersDto,
            List<SubstitutionDto> substitutionsDto,
            Question question);

    List<MatchAnswer> createMatchAnswerWithQuestion(
            List<MatchAnswerDto> matchAnswersDto, Question question);

    List<SortAnswer> createSortAnswerWithQuestion(
            List<SortAnswerDto> sortAnswersDto, Question question);

    List<Substitution> createSubstitutions(
            List<SubstitutionDto> substitutionsDto,
            List<Answer> answers,
            Question question);

    List<Answer> updateAnswersInQuestion(List<AnswerDto> answersDto);

    Answer updateAnswer(AnswerDto answerDto);

    Answer createAnswer(AnswerDto answerDto, Question question);

    Answer saveAnswer(Answer answer);

    MatchAnswer saveMatchAnswer(MatchAnswer matchAnswer);

    SortAnswer saveSortAnswer(SortAnswer sortAnswer);

    List<Answer> saveAnswers(List<Answer> answers);

    List<MatchAnswer> saveMatchAnswers(List<MatchAnswer> matchAnswers);

    List<SortAnswer> saveSortAnswers(List<SortAnswer> sortAnswers);

    Answer deleteAnswer(UUID answerId);
}
