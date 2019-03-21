package com.bsuir.cognispect.security.provider;

import com.bsuir.cognispect.security.token.TokenAuthentication;
import com.bsuir.cognispect.security.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtUtil jwtUtil;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(final Authentication authentication)
            throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;

        if (jwtUtil.validateToken(tokenAuthentication.getName())) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(
                    jwtUtil.getUsernameFromJWT(tokenAuthentication.getName()));

            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);

            return tokenAuthentication;
        } else {
            throw new IllegalArgumentException("Bad token");
        }
    }

    @Override
    public boolean supports(final Class<?> aClass) {
        return TokenAuthentication.class.equals(aClass);
    }
}
