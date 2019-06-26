package com.bsuir.cognispect.service;

import com.bsuir.cognispect.entity.ChooseAnswer;
import com.bsuir.cognispect.entity.MatchAnswer;
import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.SortAnswer;
import com.bsuir.cognispect.model.answer.ChooseAnswerModel;
import com.bsuir.cognispect.model.answer.MatchAnswerModel;
import com.bsuir.cognispect.model.answer.SortAnswerModel;
import com.bsuir.cognispect.model.question.*;

import java.util.List;
import java.util.UUID;


public interface AnswerService {
    List<ChooseAnswer> createChooseAnswers(
            List<CreateChooseAnswerModel> createChooseAnswerModels,
            Question question);

    List<ChooseAnswer> createSubstitutionAnswers(
            List<CreateChooseAnswerModel> createChooseAnswerModels,
            List<CreateSubstitutionModel> substitutionModels,
            Question question);

    List<MatchAnswer> createMatchAnswers(
            List<CreateMatchAnswerModel> createMatchAnswerModels, Question question);

    List<SortAnswer> createSortAnswers(
            List<CreateSortAnswerModel> createSortAnswerModels, Question question);

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
