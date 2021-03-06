package com.mbicycle.imarket.utils.converters.impl;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import com.mbicycle.imarket.beans.dto.UserDTO;

import java.util.stream.Collectors;

public class UserConverter extends AbstractConverter<User, UserDTO> {

    @Override
    public void convert(User source, UserDTO target) {

        target.setId(source.getId());
        target.setLogin(source.getLogin());
        target.setPassword(source.getPassword());
        target.setRoles(source.getRoles()
                              .stream()
                              .map(role -> role.getRole().name())
                              .collect(Collectors.toList()));
    }
}
