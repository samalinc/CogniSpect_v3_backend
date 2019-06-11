package com.bsuir.cognispect.model.security;

import com.bsuir.cognispect.model.user.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorizationResponseModel {
    private String authToken;
    private UserModel user;
}
