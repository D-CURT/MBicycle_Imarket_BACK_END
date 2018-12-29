package com.mbicycle.imarket.utils.converters.impl.reversed;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.services.interfaces.UserService;
import com.mbicycle.imarket.utils.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

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
        String login = source.getLogin();
<<<<<<< HEAD:src/main/java/com/mbicycle/imarket/converters/impl/reversed/ReversedProfileConverter.java
        if (userService.add(new User(login
                , source.getPassword()
                , source.getRoles()
                .stream()
                .map(s -> RoleType.valueOf(s).getRole())
                .collect(Collectors.toList())))) {

            target.setUser(userService.get(login));

        }
=======
        userService.add(new User(login
                , source.getPassword()
                , source.getRoles()
                        .stream()
                        .map(s -> RoleType.valueOf(s).getRole())
                        .collect(Collectors.toList())));
        target.setUser(userService.get(login));
>>>>>>> 6703bc28637e5ae04c375c3bd4f20c186aec383b:src/main/java/com/mbicycle/imarket/utils/converters/impl/reversed/ReversedProfileConverter.java
    }
}
