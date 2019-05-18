package com.bsuir.cognispect.model.test;

import com.bsuir.cognispect.entity.enums.TestVariantStatusEnum;
import com.bsuir.cognispect.model.question.QuestionVariantModel;
import com.bsuir.cognispect.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestVariantModel {
    private UUID id;

    private TestVariantStatusEnum testVariantStatus;

    private List<QuestionVariantModel> questionVariants;
}
