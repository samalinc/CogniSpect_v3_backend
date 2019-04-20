package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.StudentDto;
import com.bsuir.cognispect.entity.Student;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class StudentMapper {
    @Autowired
    private AccountMapper accountMapper;

    public StudentDto studentToStudentDto(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .studyGroup(student.getStudyGroup())
                .accountDto(accountMapper.accountToAccountDto(
                        student.getAccount()))
                .build();
    }
}
