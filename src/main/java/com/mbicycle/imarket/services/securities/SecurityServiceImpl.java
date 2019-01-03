package com.mbicycle.imarket.services.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service

public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String findLoggedInUsername() {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    @Override
    public User findLoggedUser() {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails;//Contains [username, password, Set<String> SimpleGrantedAuthority (.getAuthority() = String)]
    }

    @Override
    public Collection<SimpleGrantedAuthority> getRoles() {
//        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Set<String> setOfRoles = new HashSet<String>();
//        for ( GrantedAuthority authority : userDetails.getAuthorities()) {
//            setOfRoles.add(authority.getAuthority());
//        }
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        return authorities;
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            System.out.println("***SOUT***[autoLogin]: Successfully auto logged user with login = " + username);
        }
    }
}
