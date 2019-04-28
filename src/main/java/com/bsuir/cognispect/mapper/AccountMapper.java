package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.AccountDto;
import com.bsuir.cognispect.entity.Account;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class AccountMapper {
    public abstract AccountDto accountToAccountDto(Account account);

    public abstract List<AccountDto> accountsToAccountsDto(
            Collection<Account> answers);
}
