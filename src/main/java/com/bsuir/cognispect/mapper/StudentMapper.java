package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.StudentDto;
import com.bsuir.cognispect.entity.Student;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class StudentMapper {
    public abstract StudentDto entityToModel(Student student);
}
