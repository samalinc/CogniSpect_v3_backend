package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.model.TeacherModel;
import com.bsuir.cognispect.entity.Teacher;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class TeacherMapper {
    public abstract TeacherModel entityToModel(Teacher teacher);
}
