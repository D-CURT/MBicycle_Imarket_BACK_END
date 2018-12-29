package com.mbicycle.imarket.utils.converters.impl.reversed;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import com.mbicycle.imarket.beans.dto.UserDTO;
import com.mbicycle.imarket.utils.enums.RoleType;

import java.util.stream.Collectors;

public class ReversedUserConverter extends AbstractConverter<UserDTO, User> {
    @Override
    public void convert(UserDTO source, User target) {
        target.setLogin(source.getLogin());
        target.setPassword(source.getPassword());
        target.setRoles(source.getRoles()
                              .stream()
                              .map(s -> RoleType.valueOf(s).getRole())
                              .collect(Collectors.toList()));
    }
}
