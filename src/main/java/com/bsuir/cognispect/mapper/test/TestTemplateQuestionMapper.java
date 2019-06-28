package com.bsuir.cognispect.mapper.test;

import com.bsuir.cognispect.model.test.TestTemplateQuestionModel;
import com.bsuir.cognispect.entity.TestTemplateQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class TestTemplateQuestionMapper {
    @Mapping(source = "question.id", target = "questionId")
    public abstract TestTemplateQuestionModel entityToModel(
            TestTemplateQuestion testTemplateQuestion);

    public abstract List<TestTemplateQuestionModel> entitiesToModels(
            Collection<TestTemplateQuestion> testTemplateQuestions);
}
