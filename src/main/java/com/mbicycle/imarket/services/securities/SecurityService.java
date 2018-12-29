package com.mbicycle.imarket.services.securities;

/**
 * Service for Security.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public interface SecurityService {

    String findLoggedInUsername(); //тут достается имя залогенного пользователя

    void autoLogin(String username, String password); //автологин нужный после регистрации
}
