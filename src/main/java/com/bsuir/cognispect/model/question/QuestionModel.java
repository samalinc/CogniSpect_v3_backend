package com.bsuir.cognispect.model.question;

import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import com.bsuir.cognispect.model.answer.ChooseAnswerModel;
import com.bsuir.cognispect.model.answer.MatchAnswerModel;
import com.bsuir.cognispect.model.answer.SortAnswerModel;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionModel {
    private UUID id;
    @NotBlank
    @Size(max = 40)
    private String description;
    private QuestionTypeEnum type;
    private List<ChooseAnswerModel> answers;
    private List<MatchAnswerModel> matchAnswers;
    private List<SortAnswerModel> sortAnswers;
    private List<SubstitutionModel> substitutions;
    private TopicModel topic;
}
