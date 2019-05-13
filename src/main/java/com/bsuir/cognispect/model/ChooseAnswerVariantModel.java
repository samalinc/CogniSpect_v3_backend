package com.bsuir.cognispect.model;

import com.bsuir.cognispect.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChooseAnswerVariantModel {
    @JsonView(View.DefaultView.class)
    private UUID id;

    @JsonView(View.DefaultView.class)
    private String text;

    @JsonView(View.QuestionVariantView.class)
    boolean isCorrect;

    @JsonView(View.DefaultView.class)
    private int position;

    @JsonView(View.QuestionVariantView.class)
    private boolean isStudentChose;
}
