package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.converters.Converter;
import com.mbicycle.imarket.dto.UserDTO;
import com.mbicycle.imarket.facades.interfaces.UserFacade;
import com.mbicycle.imarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleUserFacade implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private Converter<UserDTO, User> converter;

    public boolean addUser(UserDTO dto) {

        converter.convert(dto);
        return false;
    }
}
