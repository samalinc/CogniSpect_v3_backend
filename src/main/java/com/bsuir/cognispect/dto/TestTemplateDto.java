package com.bsuir.cognispect.dto;

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
public class TestTemplateDto {
    private UUID id;

    @NotNull
    private UserDto creator;

    @Valid
    private List<TestTemplateQuestionDto> testTemplateQuestions;

    @NotBlank
    private String name;
}
