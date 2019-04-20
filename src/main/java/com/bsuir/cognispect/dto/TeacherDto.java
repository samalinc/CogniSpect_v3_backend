package com.bsuir.cognispect.dto;

import lombok.*;

import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private AccountDto accountDto;
}
