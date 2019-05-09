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
public class MatchAnswerVariantDto {
    @JsonView(View.DefaultView.class)
    private UUID id;

    @JsonView(View.QuestionVariantView.class)
    private String key;

    @JsonView(View.QuestionVariantView.class)
    private String value;

    @JsonView(View.DefaultView.class)
    private int keyPosition;

    @JsonView(View.DefaultView.class)
    private int valuePosition;

    @JsonView(View.QuestionVariantView.class)
    private String studentKey;

    @JsonView(View.QuestionVariantView.class)
    private String studentValue;
}
