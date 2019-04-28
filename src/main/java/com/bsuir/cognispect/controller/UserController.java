package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.dto.UserDto;
import com.bsuir.cognispect.entity.enums.RoleEnum;
import com.bsuir.cognispect.mapper.UserMapper;
import com.bsuir.cognispect.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserMapper userMapper;

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
}
