package com.mbicycle.imarket.converters;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.converters.AbstractConverter;
import com.mbicycle.imarket.dto.UserDTO;
import com.mbicycle.imarket.utils.enums.RoleType;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserConverter extends AbstractConverter<UserDTO, User> {

    @Override
    public void convert(UserDTO userDTO, User user) {
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles()
                             .stream()
                             .map(s -> RoleType.valueOf(s).getRole())
                             .collect(Collectors.toList()));
    }

    @Override
    public User convert(UserDTO source) {
        return super.convert(source);
    }
}
