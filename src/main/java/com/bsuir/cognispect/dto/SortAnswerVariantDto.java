package com.bsuir.cognispect.dto;

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
@JsonView(View.DefaultView.class)
public class SortAnswerVariantDto {
    @JsonView(View.DefaultView.class)
    private UUID id;

    @JsonView(View.DefaultView.class)
    private String text;

    @JsonView(View.QuestionVariantView.class)
    private int rightPosition;

    @JsonView(View.DefaultView.class)
    private int listPosition;

    @JsonView(View.QuestionVariantView.class)
    private int studentPosition;
}
