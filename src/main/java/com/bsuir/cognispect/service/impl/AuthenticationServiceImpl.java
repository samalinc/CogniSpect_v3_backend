package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.model.security.LoginModel;
import com.bsuir.cognispect.model.security.SignUpModel;
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
    public Account registerUser(final SignUpModel signUpModel)
            throws UniqueException {
        if (accountRepository.existsByLogin(signUpModel.getLogin())) {
            throw new UniqueException("User", "login", signUpModel.getLogin());
        }

        if (accountRepository.existsByEmail(signUpModel.getEmail())) {
            throw new UniqueException("User", "email", signUpModel.getEmail());
        }

        Account account = new Account();
        account.setLogin(signUpModel.getLogin());
        account.setHashedPassword(
                passwordEncoder.encode(signUpModel.getPassword()));
        account.setEmail(signUpModel.getEmail());

        account.setRole(signUpModel.getRole());

        if (signUpModel.getRole().name().equals(RoleEnum.STUDENT.name())) {
            Student student = new Student();
            student.setFirstName(signUpModel.getFirstName());
            student.setLastName(signUpModel.getLastName());
            student.setStudyGroup(signUpModel.getStudyGroup());
            student.setAccount(account);
            account.setStudent(student);
        } else {
            Teacher teacher = new Teacher();
            teacher.setFirstName(signUpModel.getFirstName());
            teacher.setLastName(signUpModel.getLastName());
            teacher.setAccount(account);
            account.setTeacher(teacher);
        }

        accountRepository.save(account);

        return account;
    }

    @Override
    public TokenAuthentication authenticateUser(final LoginModel loginModel)
            throws BadCredentialsException {
        Optional<Account> account = accountRepository.findByLoginOrEmail(
                loginModel.getLogin(), loginModel.getLogin());

        if (!account.isPresent() || !passwordEncoder.matches(
                loginModel.getPassword(), account.get().getHashedPassword())) {
            throw new BadCredentialsException("Incorrect login or password");
        }

        TokenAuthentication tokenAuthentication = new TokenAuthentication(
                jwtUtil.generateToken(account.get()));

        SecurityContextHolder.getContext().setAuthentication(
                authenticationManager.authenticate(tokenAuthentication));

        return tokenAuthentication;
    }
}
