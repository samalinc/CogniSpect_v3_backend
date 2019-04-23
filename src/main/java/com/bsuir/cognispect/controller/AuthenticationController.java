package com.bsuir.cognispect.controller;


import com.bsuir.cognispect.dto.AuthorizationResponseDto;
import com.bsuir.cognispect.dto.LoginDto;
import com.bsuir.cognispect.dto.SignUpDto;
import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.exception.UniqueException;
import com.bsuir.cognispect.mapper.AccountMapper;
import com.bsuir.cognispect.security.details.UserDetailsImpl;
import com.bsuir.cognispect.security.token.TokenAuthentication;
import com.bsuir.cognispect.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private AccountMapper accountMapper;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody final SignUpDto signUpDto) {
        try {
            Account account = authenticationService.registerUser(signUpDto);

            return new ResponseEntity<>(account, HttpStatus.CREATED);
        } catch (UniqueException | RoleNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(
            @Valid @RequestBody final LoginDto loginDto) {
        try {
            TokenAuthentication tokenAuthentication =
                    authenticationService.authenticateUser(loginDto);

            return ResponseEntity.ok(new AuthorizationResponseDto(
                    tokenAuthentication.getName(),
                    accountMapper.accountToAccountDto(
                            ((UserDetailsImpl) tokenAuthentication.getDetails())
                                    .getAccount())
            ));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
