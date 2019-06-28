package com.bsuir.cognispect.model.create;

import com.bsuir.cognispect.model.test.TestTemplateQuestionModel;
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
public class CreateTestTemplateModel {
    @NotNull
    private UUID creatorId;
    @Valid
    private List<TestTemplateQuestionModel> testTemplateQuestions;
    @NotBlank
    private String name;
    @NotNull
    private List<UUID> topicIds;
}
