package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.dto.SignInDto;
import com.bsuir.cognispect.dto.SignUpDto;
import com.bsuir.cognispect.dto.UserDto;
import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.entity.Role;
import com.bsuir.cognispect.entity.User;
import com.bsuir.cognispect.entity.enums.RoleEnum;
import com.bsuir.cognispect.repository.AccountRepository;
import com.bsuir.cognispect.repository.RoleRepository;
import com.bsuir.cognispect.repository.UserRepository;
import com.bsuir.cognispect.security.provider.TokenAuthenticationProvider;
import com.bsuir.cognispect.security.token.TokenAuthentication;
import com.bsuir.cognispect.security.util.JwtUtil;
import com.bsuir.cognispect.service.AuthenticationService;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    @Autowired
    private JwtUtil jwtUtil;

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

    @Override
    public ResponseEntity<?> authenticateUser(SignInDto signInDto) {
        try {
            Optional<Account> account = accountRepository.findByLogin(signInDto.getLogin());

            if (!account.isPresent()) {
                return new ResponseEntity<>(
                        "Incorrect login or password",
                        HttpStatus.BAD_REQUEST);
            }

            SecurityContextHolder.getContext().setAuthentication(
                    new TokenAuthentication(
                            jwtUtil.generateToken(account.get())
                    ));

            return ResponseEntity.ok("asasf");

        } catch (AuthenticationException ex) {
            return new ResponseEntity<>("Incorrect login or password",
                    HttpStatus.BAD_REQUEST);
        }
    }
}
