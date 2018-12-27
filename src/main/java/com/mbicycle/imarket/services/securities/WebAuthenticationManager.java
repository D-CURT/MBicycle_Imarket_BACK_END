package com.mbicycle.imarket.services.securities;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.validation.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebAuthenticationManager implements AuthenticationManager {


    @Autowired
    private UserSecurityService userService;


    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials().toString();

        User user = userService.findByLogin(username);
        if (user == null) {
            throw new RuntimeException();
        }

        if (!password.equals(user.getPassword())) {
            throw new RuntimeException();
        }

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("USER"));
        return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
    }
}
