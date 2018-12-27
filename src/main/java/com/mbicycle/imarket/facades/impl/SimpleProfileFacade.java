package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.converters.Converter;
import com.mbicycle.imarket.dto.ProfileDTO;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.services.ProfileService;
import com.mbicycle.imarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleProfileFacade implements ProfileFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private Converter<ProfileDTO, Profile> reversedConverter;

    @Override
    public boolean addProfile(ProfileDTO dto) {
        return profileService.addProfile(reversedConverter.convert(dto));
    }
}
