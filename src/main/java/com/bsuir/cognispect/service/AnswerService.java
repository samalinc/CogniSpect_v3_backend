package com.bsuir.cognispect.service;

import com.bsuir.cognispect.entity.Answer;
import com.bsuir.cognispect.entity.MatchAnswer;
import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.SortAnswer;
import com.bsuir.cognispect.model.answer.ChooseAnswerModel;
import com.bsuir.cognispect.model.answer.MatchAnswerModel;
import com.bsuir.cognispect.model.answer.SortAnswerModel;
import com.bsuir.cognispect.model.question.SubstitutionModel;

import java.util.List;
import java.util.UUID;


public interface AnswerService {
    List<Answer> createAnswers(
            List<ChooseAnswerModel> chooseAnswerModels,
            Question question);

    List<Answer> createSubstitutionAnswers(
            List<ChooseAnswerModel> chooseAnswerModels,
            List<SubstitutionModel> substitutionModels,
            Question question);

    List<MatchAnswer> createMatchAnswers(
            List<MatchAnswerModel> matchAnswerModels, Question question);

    List<SortAnswer> createSortAnswers(
            List<SortAnswerModel> sortAnswerModels, Question question);

    List<Answer> updateAnswersInQuestion(List<ChooseAnswerModel> chooseAnswerModels);

    Answer updateAnswer(ChooseAnswerModel chooseAnswerModel);

    Answer createAnswer(ChooseAnswerModel chooseAnswerModel, Question question);

    Answer deleteChooseAnswer(UUID chooseAnswerId);

    SortAnswer deleteSortAnswer(UUID sortAnswerId);

    MatchAnswer deleteMatchAnswer(UUID matchAnswerId);
}
