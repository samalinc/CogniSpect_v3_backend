package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.model.security.AuthorizationResponseModel;
import com.bsuir.cognispect.model.security.LoginModel;
import com.bsuir.cognispect.model.security.SignUpModel;
import com.bsuir.cognispect.model.user.UserModel;
import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.exception.ValidationException;
import com.bsuir.cognispect.mapper.user.UserMapper;
import com.bsuir.cognispect.security.details.UserDetailsImpl;
import com.bsuir.cognispect.security.token.TokenAuthentication;
import com.bsuir.cognispect.service.AuthenticationService;
import com.bsuir.cognispect.util.error.ApiSubError;
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
    public ResponseEntity<UserModel> registerUser(
            @RequestBody final SignUpModel signUpModel) {
        List<ApiSubError> apiSubErrors = customValidator.validateByUserRole(
                signUpModel, signUpModel.getRole());

        if (apiSubErrors != null) {
            throw new ValidationException(apiSubErrors);
        }
        Account account = authenticationService.registerUser(signUpModel);

        return new ResponseEntity<>(
                userMapper.entityToModel(account),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthorizationResponseModel> authenticateUser(
            @Valid @RequestBody final LoginModel loginModel) {
        TokenAuthentication tokenAuthentication =
                authenticationService.authenticateUser(loginModel);

        return ResponseEntity.ok(new AuthorizationResponseModel(
                tokenAuthentication.getName(), userMapper.entityToModel(
                ((UserDetailsImpl) tokenAuthentication.getDetails())
                        .getAccount())
        ));
    }
}
