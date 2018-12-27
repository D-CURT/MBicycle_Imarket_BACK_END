package com.mbicycle.imarket.converters.impl.reversed;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.converters.AbstractConverter;
import com.mbicycle.imarket.dto.ProfileDTO;
import com.mbicycle.imarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class ReversedProfileConverter extends AbstractConverter<ProfileDTO, Profile> {

    @Autowired
    @SuppressWarnings("ALL")
    private UserService userService;

    @Override
    public void convert(ProfileDTO source, Profile target) {
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        target.setPhone(source.getPhone());
        target.setAddress(source.getAddress());
        target.setUser(userService.get(source.getLogin(), source.getPassword()));
        /*userService.addUser(new User(source.getLogin()
                                   , source.getPassword()
                                   , source.getRoles()
                                           .stream()
                                           .map(s -> RoleType.valueOf(s).getRole())
                                           .collect(Collectors.toList())));*/
    }
}
