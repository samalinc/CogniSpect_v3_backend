package com.bsuir.cognispect.model.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestTemplateQuestionModel {
    @NotNull
    private UUID questionId;
    @Min(value = 1L)
    private int questionCost;
}
