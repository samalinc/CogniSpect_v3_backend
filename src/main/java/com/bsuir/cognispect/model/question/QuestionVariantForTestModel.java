package com.bsuir.cognispect.model.question;

import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import com.bsuir.cognispect.model.answer.ChooseAnswerVariantForTestModel;
import com.bsuir.cognispect.model.answer.MatchAnswerVariantForTestModel;
import com.bsuir.cognispect.model.answer.SortAnswerVariantForTestModel;
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
public class QuestionVariantForTestModel {
    private UUID id;

    private String description;

    private QuestionTypeEnum type;

    private List<ChooseAnswerVariantForTestModel> chooseAnswers;

    private List<MatchAnswerVariantForTestModel> matchAnswers;

    private List<SortAnswerVariantForTestModel> sortAnswers;
}
