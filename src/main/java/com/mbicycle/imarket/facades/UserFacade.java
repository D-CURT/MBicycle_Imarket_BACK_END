package com.mbicycle.imarket.facades;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.dto.UserDTO;
import com.mbicycle.imarket.utils.converters.Converter;
import com.mbicycle.imarket.utils.converters.reversed.ReverseUserConverter;
import com.mbicycle.imarket.utils.converters.UserConverter;
import org.springframework.stereotype.Component;

@Component
public class UserFacade implements Facade<UserDTO, User> {

    @Override
    public User push(UserDTO dto, String identifier) {
        Converter<UserDTO, User> converter = new UserConverter();
        return converter.convert(dto);
    }

    @Override
    public UserDTO pull(User user) {
        Converter<User, UserDTO> converter = new ReverseUserConverter();
        return converter.convert(user);
    }
}
