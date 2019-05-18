package com.bsuir.cognispect.mapper.test;

import com.bsuir.cognispect.model.test.TestSessionModel;
import com.bsuir.cognispect.entity.TestSession;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class TestSessionMapper {
    public abstract TestSessionModel entityToModel(TestSession testSession);

    public abstract List<TestSessionModel> entitiesToModels(
            Collection<TestSession> testSessions
    );
}
