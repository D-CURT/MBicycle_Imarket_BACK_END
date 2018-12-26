package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/users/allUsersSortedByLogin")
    public List<User> getAllUsersSortedByLogin() {
        return service.findByOrderByLogin();
    }
}
