package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.model.UserModel;
import com.bsuir.cognispect.entity.Account;
import com.bsuir.cognispect.entity.enums.RoleEnum;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    private AccountMapper accountMapper;

    public UserModel entityToModel(Account account) {
        UserModel userModel = new UserModel();

        if (account.getRole().equals(RoleEnum.STUDENT)) {
            userModel.setId(account.getStudent().getId());
            userModel.setFirstName(account.getStudent().getFirstName());
            userModel.setLastName(account.getStudent().getLastName());
            userModel.setStudyGroup(account.getStudent().getStudyGroup());
        } else {
            userModel.setId(account.getTeacher().getId());
            userModel.setFirstName(account.getTeacher().getFirstName());
            userModel.setLastName(account.getTeacher().getLastName());
        }

        userModel.setAccount(accountMapper.entityToModel(account));

        return userModel;
    }

    public abstract List<UserModel> entitiesToModels(Collection<Account> accounts);
}
