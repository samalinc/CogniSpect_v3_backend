package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.entity.enums.RoleEnum;
import com.bsuir.cognispect.exception.ValidationException;
import com.bsuir.cognispect.mapper.user.UserMapper;
import com.bsuir.cognispect.model.RestResponsePage;
import com.bsuir.cognispect.model.security.SignUpModel;
import com.bsuir.cognispect.model.user.UserModel;
import com.bsuir.cognispect.service.AccountService;
import com.bsuir.cognispect.util.error.ApiSubError;
import com.bsuir.cognispect.validation.validator.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
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
    public ResponseEntity<RestResponsePage<UserModel>> getUsersByFilter(
            @RequestParam(name = "role", required = false)
                    RoleEnum role,
            @RequestParam(name = "firstName", required = false, defaultValue = "")
                    String firstName,
            @RequestParam(name = "lastName", required = false, defaultValue = "")
                    String lastName,
            @RequestParam(name = "studyGroup", required = false, defaultValue = "")
                    String studyGroup,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(0) Integer page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "1")
            @Min(1) Integer pageSize) {

        return ResponseEntity.ok(new RestResponsePage<>(
                accountService.getAccountsByFilter(
                        role, firstName, lastName, studyGroup, page, pageSize)
                        .map(userMapper::entityToModel)));
    }

    @PutMapping
    public ResponseEntity<UserModel> updateUserAccount(
            @RequestBody final SignUpModel signUpModel) {
        List<ApiSubError> apiSubErrors = customValidator.validateByUserRole(
                signUpModel, signUpModel.getRole());

        if (apiSubErrors != null) {
            throw new ValidationException(apiSubErrors);
        }
        Account account = accountService.updateAccountInformation(signUpModel);

        return ResponseEntity.ok(userMapper.entityToModel(account));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserModel> deleteAccount(
            @PathVariable(name = "id") UUID accountId) {
        Account account = accountService.deleteAccount(accountId);

        return ResponseEntity.ok(userMapper.entityToModel(account));
    }
}
