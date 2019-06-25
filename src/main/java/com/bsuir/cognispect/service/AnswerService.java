package com.bsuir.cognispect.service;

import com.bsuir.cognispect.entity.*;
import com.bsuir.cognispect.model.answer.ChooseAnswerModel;
import com.bsuir.cognispect.model.answer.MatchAnswerModel;
import com.bsuir.cognispect.model.answer.SortAnswerModel;
import com.bsuir.cognispect.model.question.SubstitutionModel;

import java.util.List;
import java.util.UUID;


public interface AnswerService {
    List<ChooseAnswer> createChooseAnswers(
            List<ChooseAnswerModel> chooseAnswerModels,
            Question question);

    List<ChooseAnswer> createSubstitutionAnswers(
            List<ChooseAnswerModel> chooseAnswerModels,
            List<SubstitutionModel> substitutionModels,
            Question question);

    List<MatchAnswer> createMatchAnswers(
            List<MatchAnswerModel> matchAnswerModels, Question question);

    List<SortAnswer> createSortAnswers(
            List<SortAnswerModel> sortAnswerModels, Question question);

    ChooseAnswer createChooseAnswer(ChooseAnswerModel chooseAnswerModel, Question question);

    ChooseAnswer createSubstitutionAnswer(
            ChooseAnswerModel chooseAnswerModel,
            SubstitutionModel substitutionModel,
            Question question);

    MatchAnswer createMatchAnswer(MatchAnswerModel matchAnswerModel, Question question);

    SortAnswer createSortAnswer(SortAnswerModel sortAnswerModel, Question question);

    ChooseAnswer updateChooseAnswer(ChooseAnswerModel chooseAnswerModel);

    MatchAnswer updateMatchAnswer(MatchAnswerModel matchAnswerModel);

    SortAnswer updateSortAnswer(SortAnswerModel sortAnswerModel);

    ChooseAnswer deleteChooseAnswer(UUID chooseAnswerId);

    SortAnswer deleteSortAnswer(UUID sortAnswerId);

    MatchAnswer deleteMatchAnswer(UUID matchAnswerId);
}
