package com.bsuir.cognispect.model.create.answer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateSortAnswerModel {
    @NotBlank
    private String text;
    @Min(0)
    private int position;
}
