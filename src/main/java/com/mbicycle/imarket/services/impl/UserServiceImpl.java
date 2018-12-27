package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.RoleRepository;
import com.mbicycle.imarket.daos.UserRepository;
import com.mbicycle.imarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
=======
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
>>>>>>> e54693e80b86d2815b1dd9f538666a833baa7498

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User getUser(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    public boolean addUser(User user) {
        if (getUser(user.getLogin(), user.getPassword()) == null) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public void deleteUser(String login, String password) {
        userRepository.deleteByLoginAndPassword(login, password);
    }

    public List<User> findByOrderByLogin(){
        return userRepository.findByOrderByLoginAsc();
    }



    @Override
    public void save(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.getOne(1));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

}
