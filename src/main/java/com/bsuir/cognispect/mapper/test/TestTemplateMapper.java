package com.bsuir.cognispect.mapper.test;

import com.bsuir.cognispect.mapper.user.UserMapper;
import com.bsuir.cognispect.model.test.TestTemplateModel;
import com.bsuir.cognispect.entity.TestTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring",
        uses = {TestTemplateQuestionMapper.class,
                UserMapper.class})
public abstract class TestTemplateMapper {
    @Mapping(source = "testTemplate.testTemplateQuestions", target = "questions")
    public abstract TestTemplateModel entityToModel(TestTemplate testTemplate);

    public abstract List<TestTemplateModel> entitiesToModels(
            Collection<TestTemplate> testTemplates);
}
