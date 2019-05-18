package com.bsuir.cognispect.model.security;

import com.bsuir.cognispect.entity.enums.RoleEnum;
import com.bsuir.cognispect.validation.groups.AccountGroupsValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpModel {
    private UUID id;

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

    @NotBlank(groups = {
            AccountGroupsValidation.StudentValidation.class,
            AccountGroupsValidation.TeacherValidation.class})
    private String firstName;

    @NotBlank(groups = {
            AccountGroupsValidation.StudentValidation.class,
            AccountGroupsValidation.TeacherValidation.class})
    private String lastName;

    @NotBlank(groups = {AccountGroupsValidation.StudentValidation.class})
    private String studyGroup;
}
