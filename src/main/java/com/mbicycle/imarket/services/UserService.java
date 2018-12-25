package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.UserRepository;
import org.hibernate.validator.constraints.pl.NIP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User getUser(String login, String password) {
        return repository.findByLoginAndPassword(login, password);
    }

    public void addUser(User user) {
        repository.save(user);
    }

    public void deleteUser(String login, String password) {
        repository.deleteByLoginAndPassword(login, password);
    }

    public List<User> list(){
        return repository.findByOrderByLoginAsc();
    }

}
