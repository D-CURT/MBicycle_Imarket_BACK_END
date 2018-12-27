package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.UserRepository;
import com.mbicycle.imarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    public User getUser(String login, String password) {
        return repository.findByLoginAndPassword(login, password);
    }

    public boolean addUser(User user) {
        if (getUser(user.getLogin(), user.getPassword()) == null) {
            repository.save(user);
            return true;
        }
        return false;
    }

    public void deleteUser(String login, String password) {
        repository.deleteByLoginAndPassword(login, password);
    }

    public List<User> findByOrderByLogin(){
        return repository.findByOrderByLoginAsc();
    }

}
