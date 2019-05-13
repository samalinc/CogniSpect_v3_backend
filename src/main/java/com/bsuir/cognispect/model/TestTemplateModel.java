package com.bsuir.cognispect.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestTemplateModel {
    private UUID id;

    @NotNull
    private UserModel creator;

    @Valid
    private List<TestTemplateQuestionModel> testTemplateQuestions;

    @NotBlank
    private String name;
}
