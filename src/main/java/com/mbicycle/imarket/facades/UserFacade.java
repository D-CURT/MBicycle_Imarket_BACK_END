package com.mbicycle.imarket.facades;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.dto.UserDTO;
import com.mbicycle.imarket.services.UserService;
import com.mbicycle.imarket.utils.converters.Converter;
import com.mbicycle.imarket.utils.converters.reversed.ReverseUserConverter;
import com.mbicycle.imarket.utils.converters.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("ALL")
public class UserFacade implements Facade<UserDTO> {

    @Autowired
    private UserService service;

    @Override
    public boolean push(UserDTO dto, String identifier) {
        Converter<UserDTO, User> converter = new UserConverter();
        return service.addUser(converter.convert(dto));
    }

    @Override
    public UserDTO pull(UserDTO dto) {
        User user;
        if ((user = service.getUser(dto.getLogin(), dto.getPassword())) != null) {
            Converter<User, UserDTO> converter = new ReverseUserConverter();
            dto = converter.convert(user);
        } else {
            dto.setError("User not found!");
        }

        return dto;
    }
}
