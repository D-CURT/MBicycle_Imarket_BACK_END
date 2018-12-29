package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.UserRepository;
import com.mbicycle.imarket.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    @SuppressWarnings("ALL")
    private UserRepository userRepository;

    @Override
    public User get(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public User get(String login) {
        return userRepository.findOneByLogin(login);
    }

    @Override
    public String getPasswordByLogin(String login) {
       User user = userRepository.findOneByLogin(login);
       if (user == null) {
           return null;
       }
       return user.getPassword();

    }

    @Override
    public boolean add(User user) {
        String login = user.getLogin();
        String password = user.getPassword();

        if (get(login, password) == null) {
            userRepository.save(user);
        }
        return get(login, password) != null;
    }

    @Override
    public List<User> findByOrderByLogin(){
        return userRepository.findByOrderByLoginAsc();
    }

    @Override
    public boolean delete(User user) {
        String login = user.getLogin();
        String password = user.getPassword();

        if ((user = get(login, password)) != null) {
            userRepository.delete(user);
        }
        boolean d = get(login, password) == null;
        return d;
    }

}
