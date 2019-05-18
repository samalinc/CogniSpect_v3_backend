package com.bsuir.cognispect.service;

import com.bsuir.cognispect.model.security.LoginModel;
import com.bsuir.cognispect.model.security.SignUpModel;
import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.security.token.TokenAuthentication;


public interface AuthenticationService {
    Account registerUser(SignUpModel signUpModel);

    TokenAuthentication authenticateUser(LoginModel loginModel);
}
