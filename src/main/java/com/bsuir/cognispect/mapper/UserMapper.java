package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.UserDto;
import com.bsuir.cognispect.entity.Student;
import com.bsuir.cognispect.entity.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract UserDto studentToUserDto(Student student);

    public abstract UserDto teacherToUserDto(Teacher teacher);
}
