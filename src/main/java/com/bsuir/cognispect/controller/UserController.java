package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.dto.SignUpDto;
import com.bsuir.cognispect.dto.UserDto;
import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.entity.enums.RoleEnum;
import com.bsuir.cognispect.exception.ValidationException;
import com.bsuir.cognispect.mapper.UserMapper;
import com.bsuir.cognispect.service.AccountService;
import com.bsuir.cognispect.util.error.ApiSubError;
import com.bsuir.cognispect.validation.validator.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CustomValidator customValidator;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsersByFilter(
            @RequestParam(name = "role", required = false)
                    RoleEnum role,
            @RequestParam(name = "firstName", required = false, defaultValue = "")
                    String firstName,
            @RequestParam(name = "lastName", required = false, defaultValue = "")
                    String lastName,
            @RequestParam(name = "studyGroup", required = false, defaultValue = "")
                    String studyGroup) {

        return ResponseEntity.ok(userMapper.usersToUsersDto(
                accountService.getAccountsByFilter(
                        role,
                        firstName,
                        lastName,
                        studyGroup)));
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUserAccount(
            @RequestBody final SignUpDto signUpDto) {
        List<ApiSubError> apiSubErrors = customValidator.validateByUserRole(
                signUpDto, signUpDto.getRole());

        if (apiSubErrors != null) {
            throw new ValidationException(apiSubErrors);
        }
        Account account = accountService.updateAccountInformation(signUpDto);

        return ResponseEntity.ok(userMapper.userToUserDto(account));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteAccount(
            @PathVariable(name = "id") UUID accountId) {
        Account account = accountService.deleteAccount(accountId);

        return ResponseEntity.ok(userMapper.userToUserDto(account));
    }
}
