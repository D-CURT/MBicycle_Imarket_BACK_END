package com.mbicycle.imarket.utils.converters;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.dto.UserDTO;
import com.mbicycle.imarket.utils.enums.RoleType;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserConverter implements Converter<UserDTO, User> {

    @Override
    public User convert(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles()
                             .stream()
                             .map(s -> RoleType.valueOf(s).getRole())
                             .collect(Collectors.toList()));
        return user;
    }
}
