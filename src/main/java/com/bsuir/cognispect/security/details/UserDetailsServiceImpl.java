package com.bsuir.cognispect.security.details;


import com.bsuir.cognispect.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String loginOrEmail)
            throws UsernameNotFoundException {

        return UserDetailsImpl.create(
                accountRepository.findByLoginOrEmail(loginOrEmail, loginOrEmail)
                        .orElseThrow(() -> new UsernameNotFoundException(
                                "User not found with username or email: "
                                        + loginOrEmail))
        );
    }
}
