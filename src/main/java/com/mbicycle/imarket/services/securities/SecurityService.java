package com.mbicycle.imarket.services.securities;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
