package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.converters.Converter;
import com.mbicycle.imarket.dto.UserDTO;
import com.mbicycle.imarket.facades.interfaces.UserFacade;
import com.mbicycle.imarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class SimpleUserFacade implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private Converter<User, UserDTO> converter;

    @Autowired
    private Converter<UserDTO, User> reversedConverter;

    @Override
    public boolean add(UserDTO dto) {
        return userService.add(reversedConverter.convert(dto));
    }

    @Override
    public List<UserDTO> findByOrderByLogin() {
        return userService.findByOrderByLogin()
                          .stream()
                          .map(converter::convert)
                          .collect(Collectors.toList());
    }

    @Override
    public boolean delete(UserDTO dto) {
        return userService.delete(reversedConverter.convert(dto));
    }
}
