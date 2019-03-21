package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.AccountDto;
import com.bsuir.cognispect.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public abstract class AccountMapper {

    /*@Mappings({
            @Mapping(target = "hashedPassword", ignore = true),
            @Mapping(target = "user", ignore = true)
    })*/
    public abstract AccountDto accountToAccountDto(Account account);
}
