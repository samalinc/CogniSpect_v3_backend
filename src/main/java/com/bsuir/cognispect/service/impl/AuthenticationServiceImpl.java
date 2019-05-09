package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.dto.LoginDto;
import com.bsuir.cognispect.dto.SignUpDto;
import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.entity.Student;
import com.bsuir.cognispect.entity.Teacher;
import com.bsuir.cognispect.entity.enums.RoleEnum;
import com.bsuir.cognispect.exception.UniqueException;
import com.bsuir.cognispect.repository.AccountRepository;
import com.bsuir.cognispect.security.token.TokenAuthentication;
import com.bsuir.cognispect.security.util.JwtUtil;
import com.bsuir.cognispect.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Account registerUser(final SignUpDto signUpDto)
            throws UniqueException {
        if (accountRepository.existsByLogin(signUpDto.getLogin())) {
            throw new UniqueException("User", "login", signUpDto.getLogin());
        }

        if (accountRepository.existsByEmail(signUpDto.getEmail())) {
            throw new UniqueException("User", "email", signUpDto.getEmail());
        }

        Account account = new Account();
        account.setLogin(signUpDto.getLogin());
        account.setHashedPassword(
                passwordEncoder.encode(signUpDto.getPassword()));
        account.setEmail(signUpDto.getEmail());

        account.setRole(signUpDto.getRole());

        if (signUpDto.getRole().name().equals(RoleEnum.STUDENT.name())) {
            Student student = new Student();
            student.setFirstName(signUpDto.getFirstName());
            student.setLastName(signUpDto.getLastName());
            student.setStudyGroup(signUpDto.getStudyGroup());
            student.setAccount(account);
            account.setStudent(student);
        } else {
            Teacher teacher = new Teacher();
            teacher.setFirstName(signUpDto.getFirstName());
            teacher.setLastName(signUpDto.getLastName());
            teacher.setAccount(account);
            account.setTeacher(teacher);
        }

        accountRepository.save(account);

        return account;
    }

    @Override
    public TokenAuthentication authenticateUser(final LoginDto loginDto)
            throws BadCredentialsException {
        Optional<Account> account = accountRepository.findByLoginOrEmail(
                loginDto.getLogin(), loginDto.getLogin());

        if (!account.isPresent() || !passwordEncoder.matches(
                loginDto.getPassword(), account.get().getHashedPassword())) {
            throw new BadCredentialsException("Incorrect login or password");
        }

        TokenAuthentication tokenAuthentication = new TokenAuthentication(
                jwtUtil.generateToken(account.get()));

        SecurityContextHolder.getContext().setAuthentication(
                authenticationManager.authenticate(tokenAuthentication));

        return tokenAuthentication;
    }
}
