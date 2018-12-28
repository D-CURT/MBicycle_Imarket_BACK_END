package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User get(String login, String password);

    User get(String login);

    String getPasswordByLogin(String login);

    boolean add(User user);

    List<User> findByOrderByLogin();

    boolean delete(User user);

    void save(User user);
}
