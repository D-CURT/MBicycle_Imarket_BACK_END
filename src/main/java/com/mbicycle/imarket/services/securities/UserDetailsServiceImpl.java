package com.mbicycle.imarket.services.securities;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    @SuppressWarnings("ALL")
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        System.out.println("***SOUT[UserDetailsServiceImpl.loadUserByUsername()]***: login = " + login );

        User user = userRepository.findByLogin(login);

        if (user == null) {
            System.out.println("***SOUT***: User not authorized.");
            System.out.println(new BCryptPasswordEncoder().encode("admin"));
            throw new UsernameNotFoundException("***EXCEPTION***: User not authorized.");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().name()));
        }

        org.springframework.security.core.userdetails.User secUser = new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
        System.out.println("***SOUT***[secUser]: " + secUser );
        String str = new BCryptPasswordEncoder().encode(user.getPassword());
        System.out.println("***SOUT***[Encoded Password]: " + str );
        System.out.println("***SOUT***: Юзер авторизован (если дальше не будет ошибок)" );
        return secUser;
    }


}
