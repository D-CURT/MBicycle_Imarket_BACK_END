package com.mbicycle.imarket.services.securities;

import com.mbicycle.imarket.beans.entities.User;

public interface UserSecurityService {

    void save(User user);

    User findByLogin(String login);
}
