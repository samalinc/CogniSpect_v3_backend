package com.bsuir.cognispect.model.create.answer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateMatchAnswerModel {
    @NotBlank
    private String key;
    @NotBlank
    private String value;
}
