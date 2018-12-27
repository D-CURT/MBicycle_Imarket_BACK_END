package com.mbicycle.imarket.facades;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.converters.Converter;
import com.mbicycle.imarket.dto.UserDTO;
import com.mbicycle.imarket.converters.UserConverter;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    public User push(UserDTO dto) {
        Converter<UserDTO, User> converter = new UserConverter();
        return converter.convert(dto);
    }
}
