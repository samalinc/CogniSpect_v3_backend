package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.model.StudentModel;
import com.bsuir.cognispect.entity.Student;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class StudentMapper {
    public abstract StudentModel entityToModel(Student student);
}
