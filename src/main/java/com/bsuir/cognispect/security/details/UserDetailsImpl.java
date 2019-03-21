package com.bsuir.cognispect.security.details;

import com.bsuir.cognispect.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailsImpl implements UserDetails {

    private Account account;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Account account,
                           Collection<? extends GrantedAuthority> authorities) {
        this.account = account;
        this.authorities = authorities;
    }

    public static UserDetailsImpl create(Account account) {
        List<GrantedAuthority> authorities = Collections
                .singletonList(new SimpleGrantedAuthority(
                        account.getRole().getRoleName().name()));

        return new UserDetailsImpl(
                account,
                authorities
        );
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String getUsername() {
        return account.getLogin();
    }

    @Override
    public String getPassword() {
        return account.getHashedPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl otherUserDetails = (UserDetailsImpl) o;
        return Objects.equals(account.getId(), otherUserDetails.getAccount().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(account.getId());
    }
}
