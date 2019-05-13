package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.model.SignUpModel;
import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.entity.enums.RoleEnum;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.exception.UniqueException;
import com.bsuir.cognispect.repository.AccountRepository;
import com.bsuir.cognispect.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    public Account updateAccountInformation(SignUpModel signUpModel) {
        Account account = accountRepository.findById(signUpModel.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Account", signUpModel.getId()));

        if (!account.getLogin().equalsIgnoreCase(signUpModel.getLogin()) &&
                accountRepository.existsByLogin(signUpModel.getLogin())) {
            throw new UniqueException("User", "login", signUpModel.getLogin());
        }

        if (!account.getEmail().equalsIgnoreCase(signUpModel.getEmail()) &&
                accountRepository.existsByEmail(signUpModel.getEmail())) {
            throw new UniqueException("User", "email", signUpModel.getEmail());
        }

        account.setLogin(signUpModel.getLogin());
        account.setHashedPassword(
                passwordEncoder.encode(signUpModel.getPassword()));
        account.setEmail(signUpModel.getEmail());

        if (signUpModel.getRole().name().equals(RoleEnum.STUDENT.name())) {
            account.getStudent().setFirstName(signUpModel.getFirstName());
            account.getStudent().setLastName(signUpModel.getLastName());
            account.getStudent().setStudyGroup(signUpModel.getStudyGroup());
        } else {
            account.getTeacher().setFirstName(signUpModel.getFirstName());
            account.getTeacher().setLastName(signUpModel.getLastName());
        }

        accountRepository.save(account);

        return account;
    }

    @Override
    public Account deleteAccount(UUID accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException("Account", accountId));

        accountRepository.delete(account);

        return account;
    }
}
