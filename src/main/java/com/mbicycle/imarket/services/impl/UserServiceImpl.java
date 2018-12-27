package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.UserRepository;
import com.mbicycle.imarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    @SuppressWarnings("ALL")
    private UserRepository userRepository;

    @Override
    public User getUser(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public boolean addUser(User user) {
        String login = user.getLogin();
        String password = user.getPassword();

        if (getUser(login, password) == null) {
            userRepository.save(user);
        }
        return getUser(login, password) != null;
    }

    @Override
    public List<User> findByOrderByLogin(){
        return userRepository.findByOrderByLoginAsc();
    }

}
