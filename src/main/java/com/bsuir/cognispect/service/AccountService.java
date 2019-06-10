package com.bsuir.cognispect.service;

import com.bsuir.cognispect.model.security.SignUpModel;
import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.entity.enums.RoleEnum;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;


public interface AccountService {
    Page<Account> getAccountsByFilter(
            RoleEnum userType,
            String firstName,
            String lastName,
            String studyGroup,
            int page,
            int pageSize);

    Account updateAccountInformation(SignUpModel signUpModel);

    Account deleteAccount(UUID accountId);
}
