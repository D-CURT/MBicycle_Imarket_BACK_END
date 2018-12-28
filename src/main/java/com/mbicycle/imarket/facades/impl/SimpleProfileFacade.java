package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.converters.Converter;

import com.mbicycle.imarket.dto.ProfileDTO;
import com.mbicycle.imarket.dto.UserDTO;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.services.interfaces.ProfileService;
import com.mbicycle.imarket.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@SuppressWarnings("ALL")
public class SimpleProfileFacade implements ProfileFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private Converter<ProfileDTO, Profile> reversedProfileConverter;

    @Autowired
    private Converter<Profile, ProfileDTO> profileConverter;

    @Override
    public boolean add(ProfileDTO dto) {
        return profileService.add(reversedProfileConverter.convert(dto));
    }

    @Override
    public boolean delete(ProfileDTO dto) {
        return profileService.delete(reversedProfileConverter.convert(dto));
    }

    @Override
    public ProfileDTO findByUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<ProfileDTO> findByOrderByName() {
        return profileService.findByOrderByName()
                             .stream()
                             .map(profileConverter::convert)
                             .collect(Collectors.toList());
    }
}
