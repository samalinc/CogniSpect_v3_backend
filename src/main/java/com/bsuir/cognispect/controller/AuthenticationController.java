package com.bsuir.cognispect.controller;


import com.bsuir.cognispect.dto.LoginDto;
import com.bsuir.cognispect.dto.SignUpDto;
import com.bsuir.cognispect.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody final SignUpDto signUpDto)
            throws RoleNotFoundException {

        return authenticationService.registerUser(signUpDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(
            @Valid @RequestBody final LoginDto loginDto) {

        return authenticationService.authenticateUser(loginDto);
    }
}
