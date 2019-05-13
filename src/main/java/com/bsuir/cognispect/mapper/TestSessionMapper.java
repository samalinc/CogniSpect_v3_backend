package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.TestSessionDto;
import com.bsuir.cognispect.entity.TestSession;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class TestSessionMapper {
    public abstract TestSessionDto entityToModel(TestSession testSession);

    public abstract List<TestSessionDto> entitiesToModels(
            Collection<TestSession> testSessions
    );
}
