package com.bsuir.cognispect.dto;

import com.bsuir.cognispect.entity.enums.RoleEnum;
import com.bsuir.cognispect.validation.annotation.Enum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpDto {
    @NotBlank
    @Size(min = 3, max = 15)
    private String login;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @NotNull
    private RoleEnum role;
}
