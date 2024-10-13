package com.samverk.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

import com.samverk.util.ErrorMessage;
import com.samverk.util.Log;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomAuthenticationProvider(CustomUserDetailsService customUserDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            throw new AuthenticationException(ErrorMessage.REQUEST_ATTRIBUTES_ERROR) {};
        }

        HttpServletRequest request = attributes.getRequest();
        String ipAddress = getClientIP(request);

        Log.info("Login attempt for email: " + email + " IP-address: " + ipAddress);

        UserDetails userDetails = null;
        boolean isValid = false;

        try {
            userDetails = customUserDetailsService.loadUserByUsername(email);
            isValid = bCryptPasswordEncoder.matches(password, userDetails.getPassword());
        } catch (UsernameNotFoundException e) {
            Log.warn("Login failed, user: " + email + " does not exist. IP-address: " + ipAddress);
        }

        if (isValid && userDetails != null) {
            Log.info("Successful login for user: " + email + ". IP-address: " + ipAddress);
            return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());
        } else {
            if (userDetails != null) {
                Log.info("Login failed for user: " + email + ", password incorrect. IP-address: " + ipAddress);
            }
            throw new AuthenticationException(ErrorMessage.INVALID_CREDENTIALS) {};
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private String getClientIP(HttpServletRequest request) {
        String remoteAddr = Optional.ofNullable(request.getHeader("X-Forwarded-For"))
                .map(ip -> ip.split(",")[0])
                .orElse(request.getRemoteAddr());
        return remoteAddr;
    }
}
