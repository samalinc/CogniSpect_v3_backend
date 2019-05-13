package com.bsuir.cognispect.model;

import lombok.*;

import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherModel {
    private UUID id;

    private String firstName;

    private String lastName;
}
