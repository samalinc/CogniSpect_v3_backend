package com.bsuir.cognispect.mapper.user;

import com.bsuir.cognispect.model.user.AccountModel;
import com.bsuir.cognispect.entity.Account;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class AccountMapper {
    public abstract AccountModel entityToModel(Account account);

    public abstract List<AccountModel> entitiesToModels(
            Collection<Account> answers);
}
