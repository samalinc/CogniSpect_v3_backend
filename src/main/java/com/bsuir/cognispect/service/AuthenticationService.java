package com.bsuir.cognispect.service;

import com.bsuir.cognispect.dto.SignInDto;
import com.bsuir.cognispect.dto.SignUpDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> registerUser(SignUpDto SignUpDto);

    ResponseEntity<?> authenticateUser(SignInDto signInDto);
}
