package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.StudentDto;
import com.bsuir.cognispect.entity.Student;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring",
        uses = {AccountMapper.class})
public abstract class StudentMapper {
    public abstract StudentDto studentToStudentDto(Student student);
}
