package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.entity.enums.RoleEnum;
import com.bsuir.cognispect.repository.AccountRepository;
import com.bsuir.cognispect.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAccountsByFilter(
            RoleEnum userType,
            String firstName,
            String lastName,
            String studyGroup) {
        String roleName = "";

        if (userType != null) {
            roleName = userType.name();
        }

        return accountRepository.findUsersByFilter(
                roleName, firstName, lastName, studyGroup);
    }
}
