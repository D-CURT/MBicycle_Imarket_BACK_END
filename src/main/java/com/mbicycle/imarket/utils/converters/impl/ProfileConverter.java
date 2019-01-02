package com.mbicycle.imarket.utils.converters.impl;

import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.services.securities.SecurityService;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class ProfileConverter extends AbstractConverter<Profile, ProfileDTO> {

    public void convert(Profile source, ProfileDTO target) {
        User user = source.getUser();
        target.setLogin(user.getLogin());
        target.setPassword(user.getPassword());
        target.setRoles(user.getRoles()
                .stream()
                .map(role -> role.getRole().name())
                .collect(Collectors.toList()));
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        target.setPhone(source.getPhone());
        target.setAddress(source.getAddress());
    }
}
