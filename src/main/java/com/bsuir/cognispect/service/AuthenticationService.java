package com.bsuir.cognispect.service;

import com.bsuir.cognispect.dto.LoginDto;
import com.bsuir.cognispect.dto.SignUpDto;
import org.springframework.http.ResponseEntity;

import javax.management.relation.RoleNotFoundException;


public interface AuthenticationService {
    ResponseEntity<?> registerUser(SignUpDto signUpDto)
            throws RoleNotFoundException;

    ResponseEntity<?> authenticateUser(LoginDto loginDto);
}
