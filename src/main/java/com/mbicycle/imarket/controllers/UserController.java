package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.UserDTO;
import com.mbicycle.imarket.facades.interfaces.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @GetMapping("/users/allUsersSortedByLogin")
    public List<UserDTO> getAllUsersSortedByLogin() {
        return userFacade.findByOrderByLogin();
    }
}
