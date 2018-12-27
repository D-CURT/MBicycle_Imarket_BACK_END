package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.UserRepository;
import org.hibernate.validator.constraints.pl.NIP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("ALL")
public class UserService {

    @Autowired
    private UserRepository repository;

    public User getUser(String login, String password) {
        return repository.findByLoginAndPassword(login, password);
    }

    public boolean addUser(User user) {
        if (getUser(user.getLogin(), user.getPassword()) == null) {
            repository.save(user);
        }
        return true;
    }

    public void deleteUser(String login, String password) {
        repository.deleteByLoginAndPassword(login, password);
    }

    public List<User> findByOrderByLogin(){
        return repository.findByOrderByLoginAsc();
    }

}
