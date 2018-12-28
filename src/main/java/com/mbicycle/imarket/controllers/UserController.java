package com.mbicycle.imarket.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.dto.ProductDTO;
import com.mbicycle.imarket.services.UserService;
import com.mbicycle.imarket.services.securities.SecurityService;
import com.mbicycle.imarket.services.securities.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/users/allUsersSortedByLogin")
    public List<User> getAllUsersSortedByLogin() {
        return userService.findByOrderByLogin();
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity registration(@RequestParam("username") String login, @RequestParam("password") String password) {
        System.out.println("registration");
        //       User user = new User(login, password);
  //      System.out.println("registration user = " + user);

 //        userValidator.validate(user, bindingResult);

//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//
//        userService.save(user);
//        securityService.autoLogin(user.getLogin(), user.getPassword());

        return new ResponseEntity(HttpStatus.OK);
    }
}
