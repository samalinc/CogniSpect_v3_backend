package com.bsuir.cognispect.model.user;

import lombok.*;

import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModel {
    private UUID id;
    private String firstName;
    private String lastName;
    private String studyGroup;
    private AccountModel account;
}
