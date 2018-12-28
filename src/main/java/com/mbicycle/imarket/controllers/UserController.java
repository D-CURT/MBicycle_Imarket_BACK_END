package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.RoleRepository;
import com.mbicycle.imarket.dto.UserDTO;
import com.mbicycle.imarket.services.UserService;
import com.mbicycle.imarket.services.securities.SecurityService;
import com.mbicycle.imarket.services.securities.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
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

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users/allUsersSortedByLogin")
    public List<User> getAllUsersSortedByLogin() {
        return userService.findByOrderByLogin();
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity registration(@RequestBody UserDTO userDto, BindingResult bindingResult) {

        System.out.println("\nregistration");
        System.out.println(userDto.getLogin());
        System.out.println(userDto.getPassword());

        User user = new User(userDto.getLogin(), userDto.getLogin());

        userValidator.validate(user, bindingResult);

        System.out.println("\nbindingResult = " + bindingResult.getModel());
        if (bindingResult.hasErrors()) {
            System.out.println("\nHAS ERROR");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.getOne(29));
        user.setRoles(roles);
        userService.save(user);

        securityService.autoLogin(user.getLogin(), user.getPassword());

        return new ResponseEntity(HttpStatus.OK);
    }
}
