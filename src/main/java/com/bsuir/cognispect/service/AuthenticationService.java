package com.bsuir.cognispect.service;

import com.bsuir.cognispect.dto.LoginDto;
import com.bsuir.cognispect.dto.SignUpDto;
import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.security.token.TokenAuthentication;

import javax.management.relation.RoleNotFoundException;


public interface AuthenticationService {
    Account registerUser(SignUpDto signUpDto)
            throws RoleNotFoundException;

    TokenAuthentication authenticateUser(LoginDto loginDto);
}
