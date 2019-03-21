package com.bsuir.cognispect.controller;


import com.bsuir.cognispect.dto.SignInDto;
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
    AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody SignUpDto signUpDto)
            throws RoleNotFoundException {

        return authenticationService.registerUser(signUpDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(
            @Valid @RequestBody SignInDto signInDto) {

        return authenticationService.authenticateUser(signInDto);
    }

    @GetMapping("/hi")
    public String getHi() {
        return "Hello world";
    }
}
