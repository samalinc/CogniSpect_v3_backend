package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.TestTemplateQuestionDto;
import com.bsuir.cognispect.entity.TestTemplateQuestion;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class TestTemplateQuestionMapper {
    public abstract TestTemplateQuestionDto entityToModel(
            TestTemplateQuestion testTemplateQuestion);

    public abstract List<TestTemplateQuestionDto> entitiesToModels(
            Collection<TestTemplateQuestion> testTemplateQuestions);
}
