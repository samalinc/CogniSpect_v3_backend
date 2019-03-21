package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.AccountDto;
import com.bsuir.cognispect.entity.Account;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class AccountMapper {

    public abstract AccountDto accountToAccountDto(Account account);
}
