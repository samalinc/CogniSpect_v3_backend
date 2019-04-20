package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.TeacherDto;
import com.bsuir.cognispect.entity.Teacher;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class TeacherMapper {
    @Autowired
    private AccountMapper accountMapper;

    public TeacherDto teacherToTeacherDto(Teacher teacher) {
        return TeacherDto.builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .accountDto(accountMapper.accountToAccountDto(
                        teacher.getAccount()))
                .build();
    }
}
