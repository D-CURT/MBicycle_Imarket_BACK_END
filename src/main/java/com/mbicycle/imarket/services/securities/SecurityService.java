package com.mbicycle.imarket.services.securities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

/**
 * Service for Security.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public interface SecurityService {

    String findLoggedInUsername(); //тут достается имя залогенного пользователя

    void autoLogin(String username, String password); //автологин нужный после регистрации

    User findLoggedUser();

    Set<String> getRoles();

}
