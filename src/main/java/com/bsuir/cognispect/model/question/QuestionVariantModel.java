package com.bsuir.cognispect.model.question;

import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import com.bsuir.cognispect.model.answer.ChooseAnswerVariantModel;
import com.bsuir.cognispect.model.answer.MatchAnswerVariantModel;
import com.bsuir.cognispect.model.answer.SortAnswerVariantModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionVariantModel {
    private UUID id;

    private String description;

    private QuestionTypeEnum type;

    private boolean isAnswered;

    private List<ChooseAnswerVariantModel> chooseAnswers;

    private List<MatchAnswerVariantModel> matchAnswers;

    private List<SortAnswerVariantModel> sortAnswers;
}
