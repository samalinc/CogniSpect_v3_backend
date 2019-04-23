package com.bsuir.cognispect.service;

import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.entity.enums.RoleEnum;

import java.util.List;


public interface AccountService {
    List<Account> getAccountsByFilter(
            RoleEnum userType,
            String firstName,
            String lastName,
            String studyGroup);
}
