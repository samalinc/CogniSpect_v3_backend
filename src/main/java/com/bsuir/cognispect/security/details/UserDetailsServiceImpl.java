package com.bsuir.cognispect.security.details;


import com.bsuir.cognispect.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String loginOrEmail)
            throws UsernameNotFoundException {

        return UserDetailsImpl.create(
                accountRepository.findByLoginOrEmail(loginOrEmail, loginOrEmail)
                        .orElseThrow(() -> new UsernameNotFoundException(
                                "User not found with username or email: "
                                        + loginOrEmail))
        );
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(UUID id) {

        return UserDetailsImpl.create(accountRepository.findById(id)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "User not found with id: " + id))
        );
    }
}
