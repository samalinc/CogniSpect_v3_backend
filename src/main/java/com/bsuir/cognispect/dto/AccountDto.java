package com.bsuir.cognispect.dto;

import com.bsuir.cognispect.entity.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto {
    private UUID id;
    private String login;
    private RoleEnum roleEnum;
    private String email;
}
