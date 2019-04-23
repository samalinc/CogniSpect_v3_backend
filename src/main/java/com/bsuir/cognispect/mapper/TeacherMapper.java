package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.TeacherDto;
import com.bsuir.cognispect.entity.Teacher;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class TeacherMapper {
    public abstract TeacherDto teacherToTeacherDto(Teacher teacher);
}
