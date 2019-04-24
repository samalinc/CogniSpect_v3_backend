package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.AccountDto;
import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.entity.enums.RoleEnum;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class AccountMapper {
    @Autowired
    private UserMapper userMapper;

    public AccountDto accountToAccountDto(Account account) {
        if (account == null) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setId(account.getId());
        accountDto.setLogin(account.getLogin());
        accountDto.setRole(account.getRole());
        accountDto.setEmail(account.getEmail());

        if (account.getRole().equals(RoleEnum.STUDENT)) {
            accountDto.setUser(userMapper.studentToUserDto(account.getStudent()));
        } else {
            accountDto.setUser(userMapper.teacherToUserDto(account.getTeacher()));
        }

        return accountDto;
    }

    public abstract List<AccountDto> accountsToAccountsDto(
            Collection<Account> answers);
}
