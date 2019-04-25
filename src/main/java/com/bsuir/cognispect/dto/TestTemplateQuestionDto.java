package com.bsuir.cognispect.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestTemplateQuestionDto {
    private QuestionDto question;

    @Min(value = 1L)
    private int questionCost;
}
