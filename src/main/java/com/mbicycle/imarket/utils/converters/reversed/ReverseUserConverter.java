package com.mbicycle.imarket.utils.converters.reversed;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.dto.UserDTO;
import com.mbicycle.imarket.utils.converters.Converter;

import java.util.stream.Collectors;

public class ReverseUserConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles()
                             .stream()
                             .map(role -> role.getRole().name())
                             .collect(Collectors.toList()));
        return userDTO;
    }
}
