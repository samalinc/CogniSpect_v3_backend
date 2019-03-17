package com.bsuir.cognispect.service;

import com.bsuir.cognispect.dto.SignUpDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> registerUser(SignUpDto SignUpDto);
}
