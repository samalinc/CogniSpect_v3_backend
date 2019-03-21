package com.bsuir.cognispect.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignInDto {
    @NotBlank
    @Size(min = 3, max = 15)
    private String login;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
