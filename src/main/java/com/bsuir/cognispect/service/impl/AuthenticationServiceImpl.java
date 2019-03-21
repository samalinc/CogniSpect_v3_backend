package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.dto.SignUpDto;
import com.bsuir.cognispect.dto.UserDto;
import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.entity.Role;
import com.bsuir.cognispect.entity.User;
import com.bsuir.cognispect.entity.enums.RoleEnum;
import com.bsuir.cognispect.repository.AccountRepository;
import com.bsuir.cognispect.repository.RoleRepository;
import com.bsuir.cognispect.repository.UserRepository;
import com.bsuir.cognispect.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> registerUser(SignUpDto signUpDto) {
        if (accountRepository.existsByLogin(signUpDto.getLogin())) {
            return new ResponseEntity<>(
                    "User with this login already exists",
                    HttpStatus.BAD_REQUEST);
        }

        if (accountRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>(
                    "User with this email already exists",
                    HttpStatus.BAD_REQUEST);
        }

        Account account = new Account();
        account.setLogin(signUpDto.getLogin());
        account.setHashedPassword(passwordEncoder.encode(signUpDto.getPassword()));
        account.setEmail(signUpDto.getEmail());

        Role userRole = roleRepository.findByRoleName(RoleEnum.STUDENT).get();

        account.setRole(userRole);
        accountRepository.save(account);

        User user = new User();
        user.setAccount(account);
        userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(account.getId());
        userDto.setLogin(account.getLogin());
        userDto.setEmail(account.getEmail());

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
