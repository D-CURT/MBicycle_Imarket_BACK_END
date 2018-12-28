package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.UserDTO;
import com.mbicycle.imarket.facades.interfaces.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    @SuppressWarnings("ALL")
    private UserFacade userFacade;

    @GetMapping("/users/allUsersSortedByLogin")
    public List<UserDTO> getAllUsersSortedByLogin() {
        return userFacade.findByOrderByLogin();
    }

    @GetMapping("/users/add")
    public ResponseEntity addUser(UserDTO dto) {
        return userFacade.add(dto) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users/delete")
    public ResponseEntity deleteUser(UserDTO dto) {
        return userFacade.delete(dto) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
