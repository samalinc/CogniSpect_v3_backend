package com.bsuir.cognispect.mapper.test;

import com.bsuir.cognispect.entity.TestSession;
import com.bsuir.cognispect.model.test.TestSessionSimpleModel;
import com.bsuir.cognispect.model.test.TestSessionModel;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring",
        uses = {TestVariantMapper.class})
public abstract class TestSessionMapper {
    public abstract TestSessionModel entityToModel(TestSession testSession);

    public abstract TestSessionSimpleModel entityToModelForStudent(TestSession testSession);

    public abstract List<TestSessionModel> entitiesToModels(
            Collection<TestSession> testSessions);

    public abstract List<TestSessionSimpleModel> entitiesToModelsForStudent(
            Collection<TestSession> testSessions);
}
