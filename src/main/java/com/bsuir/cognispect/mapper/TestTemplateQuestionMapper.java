package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.model.TestTemplateQuestionModel;
import com.bsuir.cognispect.entity.TestTemplateQuestion;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class TestTemplateQuestionMapper {
    public abstract TestTemplateQuestionModel entityToModel(
            TestTemplateQuestion testTemplateQuestion);

    public abstract List<TestTemplateQuestionModel> entitiesToModels(
            Collection<TestTemplateQuestion> testTemplateQuestions);
}
