package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.UserDto;
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

    public UserDto userToUserDto(Account account) {
        UserDto userDto = new UserDto();

        if (account.getRole().equals(RoleEnum.STUDENT)) {
            userDto.setId(account.getStudent().getId());
            userDto.setFirstName(account.getStudent().getFirstName());
            userDto.setLastName(account.getStudent().getLastName());
            userDto.setStudyGroup(account.getStudent().getStudyGroup());
        } else {
            userDto.setId(account.getTeacher().getId());
            userDto.setFirstName(account.getTeacher().getFirstName());
            userDto.setLastName(account.getTeacher().getLastName());
        }

        userDto.setAccount(accountMapper.accountToAccountDto(account));

        return userDto;
    }

    public abstract List<UserDto> usersToUsersDto(Collection<Account> accounts);
}
