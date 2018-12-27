package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getUser(String login, String password);

    List<User> findByOrderByLogin();

    boolean addUser(User user);

    void deleteUser(String login, String password);
}
