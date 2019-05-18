package com.bsuir.cognispect.model.user;

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
public class AccountModel {
    private UUID id;

    private String login;

    private RoleEnum role;

    private String email;
}
