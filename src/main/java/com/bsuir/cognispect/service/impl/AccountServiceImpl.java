package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.dto.SignUpDto;
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
    public Account updateAccountInformation(SignUpDto signUpDto) {
        Account account = accountRepository.findById(signUpDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Account", signUpDto.getId()));

        if (!account.getLogin().equalsIgnoreCase(signUpDto.getLogin()) &&
                accountRepository.existsByLogin(signUpDto.getLogin())) {
            throw new UniqueException("User", "login", signUpDto.getLogin());
        }

        if (!account.getEmail().equalsIgnoreCase(signUpDto.getEmail()) &&
                accountRepository.existsByEmail(signUpDto.getEmail())) {
            throw new UniqueException("User", "email", signUpDto.getEmail());
        }

        account.setLogin(signUpDto.getLogin());
        account.setHashedPassword(
                passwordEncoder.encode(signUpDto.getPassword()));
        account.setEmail(signUpDto.getEmail());

        if (signUpDto.getRole().name().equals(RoleEnum.STUDENT.name())) {
            account.getStudent().setFirstName(signUpDto.getFirstName());
            account.getStudent().setLastName(signUpDto.getLastName());
            account.getStudent().setStudyGroup(signUpDto.getStudyGroup());
        } else {
            account.getTeacher().setFirstName(signUpDto.getFirstName());
            account.getTeacher().setLastName(signUpDto.getLastName());
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
