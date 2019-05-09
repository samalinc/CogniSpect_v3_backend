package com.bsuir.cognispect.dto;

import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import com.bsuir.cognispect.view.View;
import com.fasterxml.jackson.annotation.JsonView;
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
public class QuestionVariantDto {
    @JsonView(View.DefaultView.class)
    private UUID id;

    @JsonView(View.DefaultView.class)
    private String description;

    @JsonView(View.DefaultView.class)
    private QuestionTypeEnum type;

    @JsonView(View.QuestionVariantView.class)
    private boolean isAnswered;

    @JsonView(View.DefaultView.class)
    private List<AnswerVariantDto> answers;

    @JsonView(View.DefaultView.class)
    private List<MatchAnswerVariantDto> matchAnswers;

    @JsonView(View.DefaultView.class)
    private List<SortAnswerVariantDto> sortAnswers;
}
