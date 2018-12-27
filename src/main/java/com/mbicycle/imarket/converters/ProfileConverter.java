package com.mbicycle.imarket.converters;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.converters.AbstractConverter;
import com.mbicycle.imarket.dto.ProfileDTO;
import com.mbicycle.imarket.dto.UserDTO;
import com.mbicycle.imarket.services.ProfileService;
import com.mbicycle.imarket.services.UserService;
import com.mbicycle.imarket.utils.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

public class ProfileConverter extends AbstractConverter<ProfileDTO, Profile> {
    @Override
    public void convert(ProfileDTO profileDTO, Profile profile) {
        Converter<UserDTO, User> converter = new UserConverter();
        UserDTO userDTO = new UserDTO(profileDTO.getLogin()
                , profileDTO.getPassword()
                , profileDTO.getRoles());
        profile.setUser(converter.convert(userDTO));
        profile.setName(profileDTO.getName());
        profile.setEmail(profileDTO.getEmail());
        profile.setPhone(profileDTO.getPhone());
        profile.setAddress(profileDTO.getAddress());
    }


}
