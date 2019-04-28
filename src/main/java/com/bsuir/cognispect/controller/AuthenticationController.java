package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.dto.AuthorizationResponseDto;
import com.bsuir.cognispect.dto.LoginDto;
import com.bsuir.cognispect.dto.SignUpDto;
import com.bsuir.cognispect.dto.UserDto;
import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.entity.enums.RoleEnum;
import com.bsuir.cognispect.exception.ValidationException;
import com.bsuir.cognispect.mapper.UserMapper;
import com.bsuir.cognispect.security.details.UserDetailsImpl;
import com.bsuir.cognispect.security.token.TokenAuthentication;
import com.bsuir.cognispect.service.AuthenticationService;
import com.bsuir.cognispect.util.error.ApiSubError;
import com.bsuir.cognispect.validation.groups.AccountGroupsValidation;
import com.bsuir.cognispect.validation.validator.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CustomValidator customValidator;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> registerUser(
            @RequestBody final SignUpDto signUpDto) {
        List<ApiSubError> apiSubErrors;

        if (signUpDto.getRole().equals(RoleEnum.STUDENT)) {
            apiSubErrors = customValidator.validate(
                    signUpDto, AccountGroupsValidation.StudentValidation.class);
        } else {
            apiSubErrors = customValidator.validate(
                    signUpDto, AccountGroupsValidation.TeacherValidation.class);
        }
        if (apiSubErrors != null) {
            throw new ValidationException(apiSubErrors);
        }
        Account account = authenticationService.registerUser(signUpDto);

        return new ResponseEntity<>(
                userMapper.userToUserDto(account),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthorizationResponseDto> authenticateUser(
            @Valid @RequestBody final LoginDto loginDto) {
        TokenAuthentication tokenAuthentication =
                authenticationService.authenticateUser(loginDto);

        return ResponseEntity.ok(new AuthorizationResponseDto(
                tokenAuthentication.getName(),
                userMapper.userToUserDto(
                        ((UserDetailsImpl) tokenAuthentication.getDetails())
                                .getAccount())
        ));
    }
}
