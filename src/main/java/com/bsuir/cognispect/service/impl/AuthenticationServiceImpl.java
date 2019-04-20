package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.dto.AuthorizationResponseDto;
import com.bsuir.cognispect.dto.LoginDto;
import com.bsuir.cognispect.dto.SignUpDto;
import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.entity.Student;
import com.bsuir.cognispect.entity.Teacher;
import com.bsuir.cognispect.entity.enums.RoleEnum;
import com.bsuir.cognispect.mapper.AccountMapper;
import com.bsuir.cognispect.repository.AccountRepository;
import com.bsuir.cognispect.repository.StudentRepository;
import com.bsuir.cognispect.repository.TeacherRepository;
import com.bsuir.cognispect.security.token.TokenAuthentication;
import com.bsuir.cognispect.security.util.JwtUtil;
import com.bsuir.cognispect.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public ResponseEntity<?> registerUser(final SignUpDto signUpDto) {
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
        account.setHashedPassword(
                passwordEncoder.encode(signUpDto.getPassword()));
        account.setEmail(signUpDto.getEmail());

        account.setRole(signUpDto.getRole());
        accountRepository.save(account);

        if (signUpDto.getRole().name().equals(RoleEnum.STUDENT.name())) {
            Student student = new Student();
            student.setFirstName(signUpDto.getFirstName());
            student.setLastName(signUpDto.getLastName());
            student.setStudyGroup(signUpDto.getStudyGroup());
            student.setAccount(account);
            // account.setStudent(student);
            studentRepository.save(student);
        } else {
            Teacher teacher = new Teacher();
            teacher.setFirstName(signUpDto.getFirstName());
            teacher.setLastName(signUpDto.getLastName());
            teacher.setAccount(account);
            // account.setTeacher(teacher);
            teacherRepository.save(teacher);
        }

        return new ResponseEntity<>(
                accountMapper.accountToAccountDto(account),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> authenticateUser(final LoginDto loginDto) {
        try {
            Optional<Account> account = accountRepository.findByLoginOrEmail(
                    loginDto.getLogin(), loginDto.getLogin());

            if (!account.isPresent()) {
                return new ResponseEntity<>(
                        "Incorrect login or password",
                        HttpStatus.BAD_REQUEST);
            }

            TokenAuthentication tokenAuthentication = new TokenAuthentication(
                    jwtUtil.generateToken(account.get()));

            SecurityContextHolder.getContext()
                    .setAuthentication(tokenAuthentication);

            return ResponseEntity.ok(new AuthorizationResponseDto(
                    tokenAuthentication.getName(),
                    accountMapper.accountToAccountDto(account.get())
            ));

        } catch (AuthenticationException ex) {
            return new ResponseEntity<>("Incorrect login or password",
                    HttpStatus.BAD_REQUEST);
        }
    }
}
