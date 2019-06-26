package com.bsuir.cognispect.model.question;

import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateQuestionModel {
    @NotBlank
    private String description;
    private QuestionTypeEnum type;
    private List<CreateChooseAnswerModel> chooseAnswers;
    private List<CreateMatchAnswerModel> matchAnswers;
    private List<CreateSortAnswerModel> sortAnswers;
    private List<CreateSubstitutionModel> substitutions;
    private UUID topicId;
}
