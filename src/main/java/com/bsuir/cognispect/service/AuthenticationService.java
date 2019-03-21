package com.bsuir.cognispect.service;

import com.bsuir.cognispect.dto.SignInDto;
import com.bsuir.cognispect.dto.SignUpDto;
import org.springframework.http.ResponseEntity;

import javax.management.relation.RoleNotFoundException;

public interface AuthenticationService {
    ResponseEntity<?> registerUser(SignUpDto SignUpDto) throws RoleNotFoundException;

    ResponseEntity<?> authenticateUser(SignInDto signInDto);
}
